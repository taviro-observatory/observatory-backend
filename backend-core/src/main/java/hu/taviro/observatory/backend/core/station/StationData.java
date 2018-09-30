package hu.taviro.observatory.backend.core.station;

import java.time.Instant;

import lombok.Data;

@Data
public class StationData {

    Instant timestamp;

    private Double temperature;

    private Double humidity;

    private Double dust;

    private Double airQuality;

}
