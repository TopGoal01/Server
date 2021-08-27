package topgoal.tube.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.RoomMember;
import topgoal.tube.entity.User;
import topgoal.tube.repository.MessageRepository;
import topgoal.tube.repository.RoomMemberRepository;
import topgoal.tube.repository.RoomRepository;
import topgoal.tube.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomMemberServiceImpl implements RoomMemberService {

    private final RoomMemberRepository repository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public List<RoomMember> getRooms(String userToken) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(userToken);
        String uid = decodedToken.getUid();
        User user = userRepository.findById(uid).get();
        return repository.findByUserId(user);
    }

    @Override
    public List<RoomMember> getUsers(String roomID) {
        Room room = roomRepository.findById(roomID).get();
        return repository.findByRoomId(room);
    }

    @Override
    @Transactional
    public Room setRoomMember(String userToken, String roomId) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(userToken);
        String uid = decodedToken.getUid();

        List<RoomMember> byUserId = repository.findByUserId(userRepository.findById(uid).get());

        if (byUserId.isEmpty()) {
            RoomMember roomMember = new RoomMember();
            Room room = roomRepository.findById(roomId).get();
            roomMember.setRoomId(room);
            roomMember.setUserId(userRepository.findById(uid).get());
            repository.save(roomMember);
            room.setUserCount(room.getUserCount() + 1);
            log.info("user : " + userToken + " joined room " + roomId);
            return room;
        }
        return roomRepository.findById(byUserId.get(0).getRoomId().getRoomId()).get();
    }

    @Override
    @Transactional
    public Room deleteRoomMember(String userToken, String roomId) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(userToken);
        String uid = decodedToken.getUid();

        Room room = roomRepository.findById(roomId).get();
        User user = userRepository.findById(uid).get();
        room.setUserCount(room.getUserCount() - 1);
        repository.deleteByRoomIdAndUserId(room, user);
        if (room.getUserCount() == 0 || room.getAdmin().equals(user) ) {
            roomRepository.deleteById(roomId);
            messageRepository.deleteByRoomId(room);
            return null;
        }
        log.info("room " + roomId + " deleted");
        return room;
    }


}
