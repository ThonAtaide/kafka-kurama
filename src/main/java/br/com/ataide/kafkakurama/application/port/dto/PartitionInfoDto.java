package br.com.ataide.kafkakurama.application.port.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartitionInfoDto {

    private int partition;
    private NodeDto leader;
    private Collection<NodeDto> replicas;
    private Collection<NodeDto> isr;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NodeDto {
        private int id;
        private String idString;
        private String host;
        private int port;
        private String rack;
    }
}
