package br.com.ataide.kafkakurama.infrastructure.messagesystem;

import br.com.ataide.kafkakurama.application.domain.mapper.TopicInfoDtoMapper;
import br.com.ataide.kafkakurama.application.port.dto.TopicInfoDto;
import br.com.ataide.kafkakurama.application.port.messagesystem.ConsumerTopicsUseCase;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaFuture;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ConsumerTopicsUseCaseImpl implements ConsumerTopicsUseCase {

    private static final TopicInfoDtoMapper MAPPER = Mappers.getMapper(TopicInfoDtoMapper.class);

    @Override
    public List<String> getTopics(final @NonNull Properties properties) {
        log.info("Getting topics");
        KafkaConsumer<String, String> consumer = null;
        Set<String> topics = null;
        try {
            consumer = new KafkaConsumer<>(properties);
             topics = consumer.listTopics().keySet();
        } catch (Exception ex) {
            log.error("Error trying to getting topics. {}", ex.getMessage());
        } finally {
            if (consumer != null) {
                consumer.close();
            }
        }
        return Optional.ofNullable(topics)
                .map(it-> it.stream().toList())
                .orElse(Collections.emptyList());
    }

    @Override
    @SneakyThrows
    public List<TopicInfoDto> getTopicInfo(
            final @NonNull Properties properties,
            final @NonNull String topicName
    ) {
        AdminClient adminClient = null;
        Collection<TopicDescription> completedFuture = null;
        try {
            adminClient = KafkaAdminClient.create(properties);
            final DescribeTopicsResult describeTopicsResult = adminClient
                    .describeTopics(Collections.singleton(topicName));
            final KafkaFuture<Map<String, TopicDescription>> kafkaTopicsFuture = describeTopicsResult
                    .allTopicNames();
            completedFuture = kafkaTopicsFuture.get().values();
        }catch (Exception ex) {
            log.error("Error fetching topic {} metadata - {}", topicName, ex.getMessage());
        } finally {
            if (adminClient != null) {
                adminClient.close();
            }
        }
        return Optional.ofNullable(completedFuture)
                .map(Collection::stream)
                .map(stream -> stream.map(it ->
                        MAPPER.from(it.name(), it.partitions()))
                        .toList()
                )
                .orElse(Collections.emptyList());
    }


}
