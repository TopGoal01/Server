package topgoal.tube.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "chatroom_member")

public class RoomMember {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joinId")
    private Integer joinId;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User userId;

    @ManyToOne(targetEntity = Room.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ROOM_ID")
    private Room roomId;

}
