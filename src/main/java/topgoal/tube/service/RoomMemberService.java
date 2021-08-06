package topgoal.tube.service;

import topgoal.tube.entity.RoomMember;

import java.util.List;

public interface RoomMemberService {
    public List<RoomMember> getRooms(String userId);
    public List<RoomMember> getUsers(String roomID);
    public void setRoomMember(String userId, String roomId);
}
