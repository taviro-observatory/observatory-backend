package hu.taviro.observatory.backend.ingester.stationdata;

import lombok.Data;

@Data
public class StationDataDTO {

    private Long timestamp;

    private Double temperature;

    private Double humidity;

    private Double dust;

    private Double airQuality;

}
