package topgoal.tube.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topgoal.tube.entity.User;
import topgoal.tube.repository.UserRepository;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User authUser(String idToken) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();

        if (userRepository.findById(uid).isPresent()) {
            User newUser = new User();
            newUser.setId(uid);
            newUser.setName(decodedToken.getName());
            userRepository.save(newUser);
            log.info("New User uid : " + uid);
            return newUser;
        }else{
            log.info("User Name :" + decodedToken.getName() + " uid : " + uid + " logged");
            return userRepository.findById(uid).get();
        }
    }

    @Override
    public User userInfo(String userId) {
        return userRepository.findById(userId).get();
    }


}
