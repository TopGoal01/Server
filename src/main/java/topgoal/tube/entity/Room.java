package topgoal.tube.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "chatroom")
public class Room {

    @Id
    @Column(name = "ROOM_ID")
    private String roomId= UUID.randomUUID().toString();

    @Column
    private String roomName;

    @Column
    private LocalDateTime createdDate = LocalDateTime.now();

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User admin;

    @Column()
    private int UserCount = 1;
}
