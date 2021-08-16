package topgoal.tube.service;

import com.google.firebase.auth.FirebaseAuthException;
import topgoal.tube.entity.User;

public interface UserService {
    public User authUser(String idToken) throws FirebaseAuthException;

    public User userInfo(String userId);
}
