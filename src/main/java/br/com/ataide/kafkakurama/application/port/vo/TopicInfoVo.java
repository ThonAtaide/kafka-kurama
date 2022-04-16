package br.com.ataide.kafkakurama.application.port.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicInfoVo {

    private String name;
    @Builder.Default
    private List<PartitionInfoVo> partitionsInfo = new ArrayList<>();

}
