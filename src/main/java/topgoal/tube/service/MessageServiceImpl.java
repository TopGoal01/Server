package topgoal.tube.service;

import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topgoal.tube.DTO.MessageDTO;
import topgoal.tube.entity.ChatMessage;
import topgoal.tube.repository.MessageRepository;
import topgoal.tube.repository.RoomRepository;
import topgoal.tube.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository repository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;


    @Override
    @Transactional
    public ChatMessage setMessage(MessageDTO message) {
        return repository.save( new ChatMessage().builder()
                .userId(userRepository.findById(message.getUserID()).get())
                .message(message.getMessage())
                .roomId(roomRepository.findById(message.getRoomId()).get()).build());
    }
}
