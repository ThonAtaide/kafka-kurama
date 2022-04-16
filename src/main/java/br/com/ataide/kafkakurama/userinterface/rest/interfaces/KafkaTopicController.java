package br.com.ataide.kafkakurama.userinterface.rest.interfaces;

import br.com.ataide.kafkakurama.application.port.vo.TopicInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/topic")
@Tag(name="Topic resource operations")
public interface KafkaTopicController {

    @GetMapping
    @Operation(description = "Return all kafka topics.")
    List<String> getTopics();

    @GetMapping("/{topicName}")
    @Operation(description = "Return information about the topic informed.")
    List<TopicInfoVo> getTopicInfo(@PathVariable(name = "topicName") String topicName);
}
