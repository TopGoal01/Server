package topgoal.tube.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDTO {
    String message;
    String userID;
    String roomId;
    LocalDateTime localDateTime;
}
