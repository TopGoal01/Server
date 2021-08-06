package topgoal.tube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.RoomMember;
import topgoal.tube.entity.User;
import topgoal.tube.repository.RoomMemberRepository;
import topgoal.tube.repository.RoomRepository;
import topgoal.tube.repository.UserRepository;

import java.util.List;

@Service
public class RoomMemberServiceImpl implements RoomMemberService {

    @Autowired
    private RoomMemberRepository repository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<RoomMember> getRooms(String userId) {
        User user = userRepository.findById(userId).get();
        return repository.findByUserId(user);
    }

    @Override
    public List<RoomMember> getUsers(String roomID) {
        Room room = roomRepository.findById(roomID).get();
        return repository.findByRoomId(room);
    }

    @Override
    public void setRoomMember(String userId, String roomId){
        RoomMember roomMember = new RoomMember();
        Room room = roomRepository.findById(roomId).get();
        roomMember.setRoomId(room);
        roomMember.setUserId(userRepository.findById(userId).get());
        repository.save(roomMember);
        room.setUserCount(room.getUserCount()+1);
    }


}
