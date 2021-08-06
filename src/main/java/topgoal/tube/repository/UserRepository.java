package topgoal.tube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import topgoal.tube.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
