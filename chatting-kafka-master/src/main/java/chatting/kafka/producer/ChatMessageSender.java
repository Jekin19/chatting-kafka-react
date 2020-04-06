package chatting.kafka.producer;

import chatting.model.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatMessageSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Chat chatMessage) {
        LOGGER.info("sending data='{}' to topic='{}'", chatMessage, topic);
        kafkaTemplate.send(topic, chatMessage.getData().getText() + " | " + chatMessage.getAuthor());
    }
}
