package topgoal.tube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.RoomMember;
import topgoal.tube.entity.User;

import java.util.List;

@Repository
public interface RoomMemberRepository extends JpaRepository<RoomMember, Integer> {

    List<RoomMember> findByUserId(User userId);
    List<RoomMember> findByRoomId(Room roomID);
    void deleteByRoomId(Room roomID);
    void deleteByRoomIdAndUserId(Room room, User user);

}
