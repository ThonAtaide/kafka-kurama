package br.com.ataide.kafkakurama.application.port.messagesystem;

import br.com.ataide.kafkakurama.application.port.dto.TopicInfoDto;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

public interface ConsumerTopicsUseCase {

    List<String> getTopics(final @NonNull Properties properties);

    List<TopicInfoDto> getTopicInfo(
            final @NonNull Properties properties,
            final @NonNull String topicName
    );
}
