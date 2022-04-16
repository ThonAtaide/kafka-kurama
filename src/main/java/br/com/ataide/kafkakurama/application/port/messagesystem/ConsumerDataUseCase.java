package br.com.ataide.kafkakurama.application.port.messagesystem;

import br.com.ataide.kafkakurama.application.port.dto.TopicInfoDto;
import lombok.NonNull;

import java.util.List;
import java.util.Properties;

public interface ConsumerDataUseCase {

    List<TopicInfoDto> getConsumerGroups(final @NonNull Properties properties);
}
