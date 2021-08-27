package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import topgoal.tube.DTO.MessageDTO;
import topgoal.tube.entity.ChatMessage;
import topgoal.tube.service.MessageService;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate template;
    private final MessageService messageService;


    @MessageMapping("/join")
    public void join(MessageDTO message) throws Exception{
        template.convertAndSend("/topic/chat/"+message.getRoomId(), message);
        log.info(message.getRoomId()+" greetings user : " + message.getUserID());
    }

    @MessageMapping("/message")
    public void message(MessageDTO message) throws Exception {
        log.info("message Set");
        messageService.setMessage(message);
        template.convertAndSend("/topic/chat/" + message.getRoomId(), message);
        log.info("roomID : "+message.getRoomId()+" user : " + message.getUserID() + "message :" +message.getMessage());

    }

    @MessageMapping("/leave")
    public void leave(MessageDTO message) throws Exception{
        template.convertAndSend("/topic/chat"+message.getRoomId(), message);
    }

}
