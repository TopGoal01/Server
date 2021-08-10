package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import topgoal.tube.entity.Room;
import topgoal.tube.service.RoomService;

import javax.transaction.Transactional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    @Autowired
    private final RoomService roomService;

    //채팅방 개설
    @PostMapping(value = "/room/{roomName}")
    @ResponseBody
    @Transactional
    public Room create(@PathVariable String roomName, @RequestParam String user) throws Exception {
        log.info("Create New Room, admin : " + user);
        return roomService.setChatRoom(roomName, user);
    }

    //채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public Room getRoom(@PathVariable String roomId) {
        log.info("get Chat Room, roomID : " + roomId);
        return roomService.getChatRoom(roomId);
    }

    //채팅방 삭제
    @DeleteMapping("/room/{roomId}")
    @Transactional
    public void deleteRoom(@PathVariable String roomId) {
        log.info(roomId + " 삭제");
        roomService.destroyChatRoom(roomId);
    }


}
