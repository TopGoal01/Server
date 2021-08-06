package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import topgoal.tube.entity.ChatMessage;
import topgoal.tube.service.MessageService;

import javax.transaction.Transactional;


@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;

    @Autowired
    private final MessageService messageService;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessage message){
        message.setMessage(message.getUserId().getName()+"님이 들어왔습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    @Transactional
    public void message(ChatMessage message){
        messageService.setMessage(message);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value="/chat/leave")
    public void leave(ChatMessage message) {
        message.setMessage(message.getUserId().getName() + "님이 퇴장하셨습니다.");
        template.convertAndSend("/sub/chat/room" + message.getRoomId(), message);

    }
}
