package topgoal.tube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ChatMessage")
public class ChatMessageDTO {
    @Id
    private String messageId;

    @Column(nullable = false, length = 32)
    private String userId;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private String message;

    @Column
    private LocalDateTime createdDate;


}
