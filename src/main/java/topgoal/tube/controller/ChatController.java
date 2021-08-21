package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import topgoal.tube.entity.ChatMessage;
import topgoal.tube.service.MessageService;

@Profile("stomp")
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;
    private final MessageService messageService;

    @MessageMapping("/join")
    public void join(ChatMessage message) {
        message.setMessage(message.getUserId().getName() + "님이 입장하셨습니다.");
        template.convertAndSend("/chat/"+message.getRoomId(), message);
    }

    @MessageMapping("/message")
    public void message(ChatMessage message) throws Exception {
        messageService.setMessage(message);
        template.convertAndSend("/chat/" + message.getRoomId(), message);
    }

}
