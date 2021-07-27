package topgoal.tube.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessage {
    private String roomId;
    private String userId;
    private String message;
    private LocalDateTime dateTime;
    private String messageId;
}
