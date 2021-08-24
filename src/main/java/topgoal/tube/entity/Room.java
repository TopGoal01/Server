package topgoal.tube.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User admin;

    @Column()
    private int UserCount = 1;
}
