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

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private final MessageService messageService;


    @MessageMapping("/chat/message")
    @Transactional
    public void message (ChatMessage message) throws Exception{
        messageService.setMessage(message);
        template.convertAndSend("/chat/" + message.getRoomId(), message);
    }

}
