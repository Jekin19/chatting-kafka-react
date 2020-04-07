package chatting.kafka.producer;

import chatting.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String,  ChatMessage> kafkaTemplate;

    @Value("${topic.message}")
    private String chat_topic;

    @Value("${topic.file-message}")
    private String file_topic;

    public void send(String topic, ChatMessage data) {
        LOGGER.info("sending data='{}' to topic='{}'", data, topic);
        Message<ChatMessage> message = MessageBuilder.withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topic).build();
        kafkaTemplate.send(message);
    }

}