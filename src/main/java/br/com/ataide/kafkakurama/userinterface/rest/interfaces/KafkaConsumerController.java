package br.com.ataide.kafkakurama.userinterface.rest.interfaces;

import br.com.ataide.kafkakurama.application.port.vo.TopicInfoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/consumer")
public interface KafkaConsumerController {

    @GetMapping("/topic")
    List<String> getTopics();

    @GetMapping("/topic/{topicName}")
    List<TopicInfoVo> getTopicInfo(@PathVariable(name = "topicName") String topicName);
}
