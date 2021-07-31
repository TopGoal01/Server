package topgoal.tube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "chatroom")
public class ChatRoomDTO {

    @Id
    private String roomId;

    @Column(nullable = true)
    private LocalDateTime createdDate;

    @Column(nullable = false, length = 32)
    private String adminId;
}
