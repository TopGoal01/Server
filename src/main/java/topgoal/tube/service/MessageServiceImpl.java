package topgoal.tube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topgoal.tube.entity.ChatMessage;
import topgoal.tube.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository repository;

    @Override
    public ChatMessage setMessage(ChatMessage message) {
        return repository.save(message);
    }
}
