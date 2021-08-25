package topgoal.tube.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topgoal.tube.entity.User;
import topgoal.tube.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public User authUser(String idToken) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();

        if (userRepository.findById(uid).isEmpty()) {
            User newUser = new User();
            newUser.setId(uid);
            newUser.setName(decodedToken.getName());
            newUser.setUserPic(decodedToken.getPicture());
            newUser.setEmail(decodedToken.getEmail());
            newUser.setIdToken(idToken);
            userRepository.save(newUser);
            log.info("New User uid : " + uid);
            return newUser;
        }else{
            log.info("User Name :" + decodedToken.getName() + " uid : " + uid + " logged");
            //토큰 갱신
            Optional<User> user = userRepository.findById(uid);
            user.ifPresent(selectUser -> {
                selectUser.setIdToken(idToken);
                User newUser = userRepository.save(selectUser);
            });
            return userRepository.findById(uid).get();
        }
    }

    @Override
    public Optional<User> userInfo(String userToken) {
        Optional<User> user = userRepository.findByIdToken(userToken).stream().findAny();
        return user;
    }
}
