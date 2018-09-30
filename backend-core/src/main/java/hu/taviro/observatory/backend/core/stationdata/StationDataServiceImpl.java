package hu.taviro.observatory.backend.core.stationdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import hu.taviro.observatory.backend.core.stationdata.messages.StationDataAdditionMessage;

@Component
@Slf4j
class StationDataServiceImpl implements StationDataService {

    @Override
    public void createStationData(StationDataAdditionMessage stationDataAdditionMessage) {
        log.info("StationDataAdditionMessage received: {}", stationDataAdditionMessage);
    }
}
