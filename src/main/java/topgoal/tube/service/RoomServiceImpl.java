package topgoal.tube.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.RoomMember;
import topgoal.tube.entity.User;
import topgoal.tube.repository.RoomMemberRepository;
import topgoal.tube.repository.RoomRepository;
import topgoal.tube.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    @Override
    public Room getChatRoom(String RoomId) {
        Optional<Room> chatRoom = roomRepository.findById(RoomId);
        if (chatRoom.isPresent()) {
            return chatRoom.get();
        }
        return null;
    }

    @Override
    public Room setChatRoom(String userToken) throws FirebaseAuthException {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(userToken);
            String uid = decodedToken.getUid();

            Room room = new Room();
            User user = userRepository.findById(uid).get();
            room.setAdmin(user);
            RoomMember roomMember = new RoomMember();
            roomMember.setRoomId(room);
            roomMember.setUserId(user);
            memberRepository.save(roomMember);
            return roomRepository.save(room);
    }

    @Override
    public void destroyChatRoom(String roomId) {
        Room room = roomRepository.findById(roomId).get();
        memberRepository.deleteByRoomId(room);
        log.info("room : " + roomId + " members deleted");
        roomRepository.deleteById(roomId);
    }

    private final RoomMemberRepository memberRepository;
}
