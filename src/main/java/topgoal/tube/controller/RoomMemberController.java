package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import topgoal.tube.entity.RoomMember;
import topgoal.tube.entity.User;
import topgoal.tube.service.RoomMemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/rooms")
@Slf4j
public class RoomMemberController {

    @Autowired
    private RoomMemberService roomMemberService;

    //특정 유저가 속해있는 모든 채팅방 정보 조회
    @GetMapping("/")
    @ResponseBody
    public List<RoomMember> userRooms(@RequestParam String userId){
        return roomMemberService.getRooms(userId);
    }


    //특정 방에 속해있는 모든 유저 정보 조회
    @GetMapping("/users")
    @ResponseBody
    public List<RoomMember> RoomUsers(@RequestParam String roomId) {
        return roomMemberService.getUsers(roomId);
    }




}
