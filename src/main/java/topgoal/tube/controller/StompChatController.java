package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import topgoal.tube.model.ChatMessageDTO;
import topgoal.tube.repository.ChatMessageRepository;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template;
    private final ChatMessageRepository chatMessageRepository;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message) throws Exception {
        message.setMessage(message.getUserId()+"이 채팅이 참가하였습니다."); //User name 으로 변경?!
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message) throws Exception{

        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setCreatedDate(LocalDateTime.now());
        chatMessageDTO.setMessageId(message.getRoomId() + message.getUserId() + chatMessageDTO.getCreatedDate());
        chatMessageDTO.setMessage(message.getMessage());
        chatMessageDTO.setRoomId(message.getRoomId());
        chatMessageDTO.setUserId(message.getUserId());
        chatMessageRepository.save(chatMessageDTO);

        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
