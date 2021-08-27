package topgoal.tube.service;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;
import topgoal.tube.entity.User;

import java.util.Optional;

public interface UserService {
    public User authUser(String idToken) throws FirebaseAuthException;

    public Optional<User> userInfo(String userId) throws FirebaseAuthException;

}
