package hu.taviro.observatory.backend.core.stationdata;

import hu.taviro.observatory.backend.core.stationdata.messages.StationDataAdditionMessage;

public interface StationDataService {

    void createStationData(StationDataAdditionMessage stationDataAdditionMessage);
}
