package hu.taviro.observatory.backend.core.station.messages;

import java.time.Instant;

import org.mapstruct.Mapper;

import hu.taviro.observatory.backend.core.station.StationData;

@Mapper(componentModel = "spring")
public interface StationMessagesMapper {

    StationData additionMessageToStationData(StationDataAdditionMessage stationDataAdditionMessage);

    default Instant longToInstant(Long timestamp) {
        return Instant.ofEpochMilli(timestamp);
    }
}
