package topgoal.tube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topgoal.tube.model.ChatMessageDTO;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageDTO, String> {
}
