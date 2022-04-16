package br.com.ataide.kafkakurama.application.domain.mapper;

import br.com.ataide.kafkakurama.application.port.dto.PartitionInfoDto;
import br.com.ataide.kafkakurama.application.port.dto.PartitionInfoDto.NodeDto;
import br.com.ataide.kafkakurama.application.port.dto.TopicInfoDto;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartitionInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper
public interface TopicInfoDtoMapper {

    @Mapping(source = "topicName", target = "name")
    @Mapping(source = "partitionsInfo", target = "partitionsInfo")
    TopicInfoDto from(final String topicName, Collection<TopicPartitionInfo> partitionsInfo);

    default List<PartitionInfoDto> mapPartitionsInfo(
            final Collection<TopicPartitionInfo> partitionsInfo
    ) {
        return partitionsInfo.stream().map(it ->
                PartitionInfoDto
                        .builder()
                        .partition(it.partition())
                        .leader(mapNode(it.leader()))
                        .replicas(it.replicas()
                                .stream().map(this::mapNode).toList()
                        ).isr(it.isr()
                                .stream().map(this::mapNode).toList()
                        ).build()
        ).toList();
    }

    default NodeDto mapNode(final Node node) {
        return NodeDto.builder()
                .id(node.id())
                .idString(node.idString())
                .host(node.host())
                .port(node.port())
                .rack(node.rack())
                .build();
    }
}
