package topgoal.tube.service;

import topgoal.tube.entity.Room;
import topgoal.tube.entity.RoomMember;

import java.util.List;

public interface RoomMemberService {
    List<RoomMember> getRooms(String userToken);
    List<RoomMember> getUsers(String roomID);
    Room setRoomMember(String userId, String roomId);
    Room deleteRoomMember(String userId, String roomId);
}
