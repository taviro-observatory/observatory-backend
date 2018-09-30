package hu.taviro.observatory.backend.ingester.stationdata;

import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import hu.taviro.observatory.backend.core.station.StationService;
import hu.taviro.observatory.backend.core.station.messages.StationDataAdditionMessage;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StationDataHandler implements MessageHandler {

    static final String HEADER_TOPIC = "mqtt_receivedTopic";

    private final StationDataMapper stationDataMapper;
    private final StationService stationService;

    @Override
    public void handleMessage(@NonNull Message<?> message) {
        validateMessage(message);
        String stationId = extractStationId(message);
        StationDataAdditionMessage additionMessage = constructAdditionMessage(message, stationId);
        stationService.createStationData(additionMessage);
    }

    private void validateMessage(Message<?> message) {
        if (!(message.getPayload() instanceof StationDataDTO)) {
            throw new MessageConversionException("StationDataHandler can only handle StationDataDTO instances");
        }
    }

    private String extractStationId(Message<?> message) {
        String topic = (String) message.getHeaders().get(HEADER_TOPIC);
        return Objects.requireNonNull(topic).split("/")[1];
    }

    private StationDataAdditionMessage constructAdditionMessage(Message<?> message, String stationId) {
        StationDataDTO stationDataDTO = (StationDataDTO) message.getPayload();
        StationDataAdditionMessage additionMessage = stationDataMapper.toAdditionMessage(stationDataDTO);
        additionMessage.setStationId(stationId);
        return additionMessage;
    }
}
