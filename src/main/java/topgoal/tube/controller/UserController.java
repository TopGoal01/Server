package topgoal.tube.controller;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import topgoal.tube.entity.User;
import topgoal.tube.service.UserService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/user/{userToken}")
    public User userAuth(@PathVariable String userToken) throws FirebaseAuthException {
        return userService.authUser(userToken);
    }

    @GetMapping("/user/{userId}")
    public User getUserInfo(@PathVariable String userId){
        return userService.userInfo(userId);
    }
}