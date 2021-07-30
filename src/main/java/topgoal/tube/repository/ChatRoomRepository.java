package topgoal.tube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topgoal.tube.model.ChatRoomDTO;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomDTO,String> {

}
