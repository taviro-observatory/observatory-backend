package hu.taviro.observatory.backend.core.station;

import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "stations")
public class Station {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private List<StationData> stationData;

    public void addStationData(StationData stationData) {
        this.stationData.add(stationData);
    }
}
