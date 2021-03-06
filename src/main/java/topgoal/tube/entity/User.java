package topgoal.tube.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @Column(name = "USER_ID")
    private String Id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String userPic;

    @Column
    private String idToken;

}
