package br.com.ataide.kafkakurama.userinterface.rest;

import br.com.ataide.kafkakurama.application.port.mapper.TopicInfoVoMapper;
import br.com.ataide.kafkakurama.application.port.service.KafkaConsumerService;
import br.com.ataide.kafkakurama.application.port.vo.TopicInfoVo;
import br.com.ataide.kafkakurama.userinterface.rest.interfaces.KafkaTopicController;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KafkaTopicControllerImpl implements KafkaTopicController {

    private static final TopicInfoVoMapper MAPPER = Mappers.getMapper(TopicInfoVoMapper.class);
    private final KafkaConsumerService kafkaConsumerService;

    @ResponseStatus(code = HttpStatus.OK, value = HttpStatus.OK)
    @Override
    public List<String> getTopics() {
        return kafkaConsumerService.getTopics();
    }

    @Override
    public List<TopicInfoVo> getTopicInfo(String topicName) {
        return kafkaConsumerService.getTopicInfo(topicName)
                .stream()
                .map(MAPPER::from)
                .toList();
    }
}
