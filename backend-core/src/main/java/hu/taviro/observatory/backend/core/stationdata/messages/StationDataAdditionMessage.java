package hu.taviro.observatory.backend.core.stationdata.messages;

import lombok.Data;

@Data
public class StationDataAdditionMessage {

    private String stationId;

    private Long timestamp;

    private Double temperature;

    private Double humidity;

    private Double dust;

    private Double airQuality;
}
