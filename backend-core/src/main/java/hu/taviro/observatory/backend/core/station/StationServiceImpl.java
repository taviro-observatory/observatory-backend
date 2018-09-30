package hu.taviro.observatory.backend.core.station;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import hu.taviro.observatory.backend.core.station.messages.StationDataAdditionMessage;
import hu.taviro.observatory.backend.core.station.messages.StationMessagesMapper;

@Component
@Slf4j
class StationServiceImpl implements StationService {

    private StationRepository stationRepository;
    private StationMessagesMapper stationMessagesMapper;

    public StationServiceImpl(StationRepository stationRepository, StationMessagesMapper stationMessagesMapper) {
        this.stationRepository = stationRepository;
        this.stationMessagesMapper = stationMessagesMapper;
    }

    @Override
    public void createStationData(StationDataAdditionMessage stationDataAdditionMessage) {
        log.info("StationDataAdditionMessage received: {}", stationDataAdditionMessage);
        Station station = stationRepository
                .findById(stationDataAdditionMessage.getStationId())
                .orElseThrow(() -> new IllegalArgumentException("No station with id " + stationDataAdditionMessage.getStationId()));

        StationData stationDataToAdd = stationMessagesMapper.additionMessageToStationData(stationDataAdditionMessage);
        station.addStationData(stationDataToAdd);
        stationRepository.save(station);
    }
}
