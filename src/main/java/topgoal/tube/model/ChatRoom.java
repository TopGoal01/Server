package topgoal.tube.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ChatRoom {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessionSet = new HashSet<>();

    public static ChatRoom create(String name){
        ChatRoom room = new ChatRoom();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        return room;
    }
}
