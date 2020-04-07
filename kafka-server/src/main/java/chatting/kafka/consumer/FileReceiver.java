
package chatting.kafka.consumer;

import chatting.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.MessageHeaders;
import java.util.concurrent.CountDownLatch;

@Service
public class FileReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @Autowired
    private SimpMessagingTemplate template;



    @KafkaListener(topics = "${topic.file-message}")
    public void receive(@Payload ChatMessage data,
                        @Headers MessageHeaders headers) {
        LOGGER.info("received data='{}'", data);

        headers.keySet().forEach(key -> {
            LOGGER.info("{}: {}", key, headers.get(key));
        });
        this.template.convertAndSend("/topic/fileMessage", data);
        latch.countDown();
    }
}