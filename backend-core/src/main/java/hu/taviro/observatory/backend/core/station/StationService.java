package hu.taviro.observatory.backend.core.station;

import hu.taviro.observatory.backend.core.station.messages.StationDataAdditionMessage;

public interface StationService {

    void createStationData(StationDataAdditionMessage stationDataAdditionMessage);
}
