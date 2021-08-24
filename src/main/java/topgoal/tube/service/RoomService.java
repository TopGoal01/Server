package topgoal.tube.service;

import org.springframework.stereotype.Service;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.User;

public interface RoomService {
    public Room getChatRoom(String roomId);
    public Room setChatRoom(String userId);
    public void destroyChatRoom(String roomId);

}
