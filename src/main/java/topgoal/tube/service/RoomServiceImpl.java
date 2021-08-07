package topgoal.tube.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.RoomMember;
import topgoal.tube.entity.User;
import topgoal.tube.repository.RoomMemberRepository;
import topgoal.tube.repository.RoomRepository;
import topgoal.tube.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Room getChatRoom(String RoomId) {
        Optional<Room> chatRoom = roomRepository.findById(RoomId);
        if (chatRoom.isPresent()) {
            return chatRoom.get();
        }
        return null;
    }

    @Override
    public Room setChatRoom(String roomName, String userId){
        if (roomRepository.findByroomName(roomName).isEmpty()) {
            Room room = new Room();
            User user = userRepository.findById(userId).get();
            room.setRoomName(roomName);
            room.setAdmin(user);
            RoomMember roomMember = new RoomMember();
            roomMember.setRoomId(room);
            roomMember.setUserId(user);
            memberRepository.save(roomMember);
            return roomRepository.save(room);
        }
        return null;
    }

    @Override
    public void destroyChatRoom(String roomId) {
        Room room = roomRepository.findById(roomId).get();
        memberRepository.deleteByRoomId(room);
        log.info("room : " + roomId + " members deleted");
        roomRepository.deleteById(roomId);
    }

}
