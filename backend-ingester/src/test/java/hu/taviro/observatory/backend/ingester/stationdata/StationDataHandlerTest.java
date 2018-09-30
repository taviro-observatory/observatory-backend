package hu.taviro.observatory.backend.ingester.stationdata;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.support.MessageBuilder;

import hu.taviro.observatory.backend.core.station.StationService;

import static org.mockito.Mockito.mock;

public class StationDataHandlerTest {

    private StationDataHandler stationDataHandler;
    private StationService stationServiceMock;
    private StationDataMapper stationDataMapperMock;

    @Before
    public void init() {
        stationServiceMock = mock(StationService.class);
        stationDataMapperMock = mock(StationDataMapper.class);
        stationDataHandler = new StationDataHandler(stationDataMapperMock, stationServiceMock);
    }

    @Test(expected = MessageConversionException.class)
    public void invalidMessage() {
        Message invalidMessage = MessageBuilder.withPayload("invalid").build();
        stationDataHandler.handleMessage(invalidMessage);
    }

    @Test
    public void extractValidStationId() {
        String topic = "station/stationId/data";
        MessageHeaders messageHeaders = new MessageHeaders(Collections.singletonMap(StationDataHandler.HEADER_TOPIC, topic));
        Message message = MessageBuilder.createMessage(new StationDataDTO(), messageHeaders);

    }
}
