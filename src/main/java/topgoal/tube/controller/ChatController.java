package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import topgoal.tube.DTO.MessageDTO;
import topgoal.tube.entity.ChatMessage;
import topgoal.tube.service.MessageService;

import java.time.LocalDateTime;

@Profile("stomp")
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;
    private final MessageService messageService;

    @MessageMapping("/join")
    public void join(MessageDTO message) throws Exception{
        template.convertAndSend("/chat/"+message.getRoomId(), message);
    }

    @MessageMapping("/message")
    public void message(MessageDTO message) throws Exception {
        message.setLocalDateTime(LocalDateTime.now());
        messageService.setMessage(message);
        template.convertAndSend("/chat/" + message.getRoomId(), message);
    }

    @MessageMapping("/leave")
    public void leave(MessageDTO message) throws Exception{
        message.setMessage(message.getUserID() + "님이 퇴장하셨습니다.");
        template.convertAndSend("/chat"+message.getRoomId(), message);
    }

}
