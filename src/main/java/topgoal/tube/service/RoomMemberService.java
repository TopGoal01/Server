package topgoal.tube.service;

import org.springframework.stereotype.Service;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.RoomMember;

import java.util.List;

@Service
public interface RoomMemberService {
    List<RoomMember> getRooms(String userId);
    List<RoomMember> getUsers(String roomID);
    Room setRoomMember(String userId, String roomId);
    Room deleteRoomMember(String userId, String roomId);
}
