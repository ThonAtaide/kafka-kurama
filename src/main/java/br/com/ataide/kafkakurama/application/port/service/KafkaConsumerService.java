package br.com.ataide.kafkakurama.application.port.service;

import br.com.ataide.kafkakurama.application.port.dto.TopicInfoDto;

import java.util.List;

public interface KafkaConsumerService {

    List<String> getTopics();

    List<TopicInfoDto> getTopicInfo(final String topicName);
}
