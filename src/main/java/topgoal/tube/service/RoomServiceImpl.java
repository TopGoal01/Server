package topgoal.tube.service;

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
    public Room setChatRoom(String roomName, String userId) {
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

    @Override
    public void destroyChatRoom(String roomId) {
        roomRepository.deleteById(roomId);
    }

}
