package topgoal.tube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topgoal.tube.entity.ChatMessage;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.User;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, String> {
    void deleteByRoomId(Room room);
    void deleteByUserId(User user);
}
