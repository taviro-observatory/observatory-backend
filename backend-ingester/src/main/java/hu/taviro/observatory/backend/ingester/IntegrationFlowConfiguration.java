package hu.taviro.observatory.backend.ingester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

import hu.taviro.observatory.backend.ingester.stationdata.StationDataDTO;
import hu.taviro.observatory.backend.ingester.stationdata.StationDataHandler;

@Configuration
class IntegrationFlowConfiguration {

    private final MQTTProperties mqttProperties;

    @Autowired
    public IntegrationFlowConfiguration(MQTTProperties mqttProperties) {
        this.mqttProperties = mqttProperties;
    }

    @Bean
    IntegrationFlow stationDataFlow(MessageChannel mqttInputChannel, StationDataHandler stationDataHandler) {
        return IntegrationFlows
                .from(mqttInputChannel)
                .transform(new JsonToObjectTransformer(StationDataDTO.class))
                .handle(stationDataHandler)
                .get();
    }

    @Bean
    MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    MessageProducer mqttInbound(MqttPahoClientFactory clientFactory,
                                MessageChannel mqttInputChannel) {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                mqttProperties.getClientId(), clientFactory, mqttProperties.getTopic());
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setOutputChannel(mqttInputChannel);
        return adapter;
    }

    @Bean
    MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setUserName(mqttProperties.getUsername());
        factory.setPassword(mqttProperties.getPassword());
        factory.setServerURIs(mqttProperties.getServerUri());
        return factory;
    }
}
