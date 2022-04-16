package br.com.ataide.kafkakurama.application.port.vo;

import br.com.ataide.kafkakurama.application.port.dto.PartitionInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartitionInfoVo {

    private int partition;
    private NodeVo leader;
    private Collection<NodeVo> replicas;
    private Collection<NodeVo> isr;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NodeVo {
        private int id;
        private String idString;
        private String host;
        private int port;
        private String rack;
    }
}
