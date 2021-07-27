package topgoal.tube.repository;

import org.springframework.stereotype.Repository;
import topgoal.tube.model.ChatRoom;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRooms(){
        List<ChatRoom> rooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(rooms);

        return rooms;
    }

    public ChatRoom findRoomById(String id){
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String uuid) {
        ChatRoom room = ChatRoom.create(uuid);
        chatRoomMap.put(room.getRoomId(), room);

        return room;
    }
}
