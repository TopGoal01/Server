package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import topgoal.tube.entity.Room;
import topgoal.tube.entity.RoomMember;
import topgoal.tube.service.RoomMemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomMemberController {

    @Autowired
    private RoomMemberService roomMemberService;

    //특정 유저가 속해있는 모든 채팅방 정보 조회
    @GetMapping("/member/rooms")
    @ResponseBody
    protected List<RoomMember> userRooms(@RequestParam String userToken) {
        return roomMemberService.getRooms(userToken);
    }

    //특정 방에 속해있는 모든 유저 정보 조회
    @GetMapping("/member/users")
    @ResponseBody
    public List<RoomMember> RoomUsers(@RequestParam String roomId) {
        return roomMemberService.getUsers(roomId);
    }

    @PostMapping("/member/{roomId}")
    @ResponseBody
    public Room enter(@PathVariable String roomId, @RequestParam String userToken) {
        return roomMemberService.setRoomMember(userToken, roomId);
    }

    @DeleteMapping("/member/{roomId}")
    public void leave(@PathVariable String roomId, @RequestParam String userToken) {
        roomMemberService.deleteRoomMember(userToken, roomId);
    }
}
