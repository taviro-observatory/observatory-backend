package hu.taviro.observatory.backend.ingester.stationdata;

import org.mapstruct.Mapper;

import hu.taviro.observatory.backend.core.station.messages.StationDataAdditionMessage;

@Mapper(componentModel = "spring")
public interface StationDataMapper {

    StationDataAdditionMessage toAdditionMessage(StationDataDTO dto);
}
