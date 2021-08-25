package topgoal.tube.service;

import org.springframework.stereotype.Service;
import topgoal.tube.DTO.MessageDTO;
import topgoal.tube.entity.ChatMessage;

public interface MessageService {

public ChatMessage setMessage(MessageDTO message);
}
