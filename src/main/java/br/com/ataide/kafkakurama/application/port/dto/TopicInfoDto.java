package br.com.ataide.kafkakurama.application.port.dto;


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
public class TopicInfoDto {

    private String name;
    @Builder.Default
    private List<PartitionInfoDto> partitionsInfo = new ArrayList<>();

}
