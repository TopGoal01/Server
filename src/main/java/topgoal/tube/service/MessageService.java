package topgoal.tube.service;

import org.springframework.stereotype.Service;
import topgoal.tube.entity.ChatMessage;

@Service
public interface MessageService {

    public ChatMessage setMessage(ChatMessage message);
}
