package topgoal.tube.service;

import org.springframework.stereotype.Service;
import topgoal.tube.entity.ChatMessage;

public interface MessageService {

    public ChatMessage setMessage(ChatMessage message);
}
