package topgoal.tube.entity;

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
@Entity(name = "ChatMessage")
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;

    @Column(nullable = false)
    private String message;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="USER_ID")
    private User userId;

    @ManyToOne(targetEntity = Room.class)
    @JoinColumn(name="ROOM_ID")
    private Room roomId;


    @Column
    private LocalDateTime createdDate = LocalDateTime.now();


}
