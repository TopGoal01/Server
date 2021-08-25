package topgoal.tube.entity;

import lombok.*;
import topgoal.tube.DTO.MessageDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ChatMessage")
@Builder
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

}
