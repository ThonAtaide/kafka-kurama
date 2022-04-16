package br.com.ataide.kafkakurama.application.port.mapper;

import br.com.ataide.kafkakurama.application.port.dto.TopicInfoDto;
import br.com.ataide.kafkakurama.application.port.vo.TopicInfoVo;
import org.mapstruct.Mapper;

@Mapper
public interface TopicInfoVoMapper {

    TopicInfoVo from(TopicInfoDto topicInfoDto);
}
