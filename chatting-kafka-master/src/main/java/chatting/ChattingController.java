package chatting;

import chatting.model.Chat;
import chatting.kafka.consumer.Receiver;
import chatting.kafka.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static String BOOT_TOPIC = "chatting";
    private static String BOOT_TOPIC_CHAT_MESSAGE = "chatting_Message";

    @MessageMapping("/message")
    public void sendMessage(ChattingMessage message) throws Exception {
        Thread.sleep(300); // simulated delay
        sender.send(BOOT_TOPIC, message.getMessage() + "|" + message.getUser());
    }

    @MessageMapping("/chatMessage")
    public void sendChatMessage(Chat message) throws Exception {
        Thread.sleep(300); // simulated delay
        sender.send(BOOT_TOPIC_CHAT_MESSAGE, message.getData().getText() + "|" + message.getAuthor());
    }

//    @MessageMapping("/file")
//    @SendTo("/topic/chatting")
//    public ChattingMessage sendFile(ChattingMessage message) throws Exception {
//        return new ChattingMessage(message.getFileName(), message.getRawData(), message.getUser());
//    }
}