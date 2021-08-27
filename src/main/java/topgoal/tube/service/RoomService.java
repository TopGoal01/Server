package topgoal.tube.service;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.User;

public interface RoomService {
    public Room getChatRoom(String roomId);
    public Room setChatRoom(String userId) throws FirebaseAuthException;
    public void destroyChatRoom(String roomId);

}
