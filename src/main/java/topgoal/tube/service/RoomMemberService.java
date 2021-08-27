package topgoal.tube.service;

import com.google.firebase.auth.FirebaseAuthException;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.RoomMember;

import java.util.List;

public interface RoomMemberService {
    List<RoomMember> getRooms(String userToken) throws FirebaseAuthException;
    List<RoomMember> getUsers(String roomID);
    Room setRoomMember(String userId, String roomId) throws FirebaseAuthException;
    Room deleteRoomMember(String userId, String roomId) throws FirebaseAuthException;
}
