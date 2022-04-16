package br.com.ataide.kafkakurama.application.domain.services;

import br.com.ataide.kafkakurama.application.port.dto.TopicInfoDto;
import br.com.ataide.kafkakurama.application.port.mapper.TopicInfoVoMapper;
import br.com.ataide.kafkakurama.application.port.messagesystem.ConsumerTopicsUseCase;
import br.com.ataide.kafkakurama.application.port.service.KafkaConsumerService;
import br.com.ataide.kafkakurama.application.port.vo.TopicInfoVo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final ConsumerTopicsUseCase consumerTopicsUseCase;

    public List<String> getTopics() {
        Properties props = getProperties();
        return consumerTopicsUseCase.getTopics(props);
    }

    @Override
    public List<TopicInfoDto> getTopicInfo(@NonNull final String topicName) {
        Properties props = getProperties();
        return consumerTopicsUseCase.getTopicInfo(props, topicName);
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test-consumer-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }


}
