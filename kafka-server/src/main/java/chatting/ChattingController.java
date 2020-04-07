package chatting;

import chatting.model.ChatMessage;
import chatting.kafka.consumer.Receiver;
import chatting.kafka.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChattingController {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Value("${topic.message}")
    private String chatting_topic;

    @Value("${topic.file-message}")
    private String file_message_topic;

    @MessageMapping("/chatMessage")
    public void sendChatMessage(ChatMessage message) throws Exception {
        Thread.sleep(100); // simulated delay
        sender.send(chatting_topic, message);
    }

    @MessageMapping("/fileMessage")
    public void sendFile(ChatMessage message) throws Exception {
        Thread.sleep(100); // simulated delay
        sender.send(file_message_topic, message);
    }
}