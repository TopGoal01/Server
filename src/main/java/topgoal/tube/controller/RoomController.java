package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.BasicResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import topgoal.tube.entity.Room;
import topgoal.tube.service.RoomService;

import javax.transaction.Transactional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    //채팅방 개설
    @PostMapping(value = "/room/{userToken}")
    @ResponseBody
    @Transactional
    public Room create(@PathVariable String userToken) throws Exception {
        log.info("Create New Room, admin : " + userToken);
        return roomService.setChatRoom(userToken);
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
    public ResponseEntity<? extends BasicResponseHandler> deleteRoom(@PathVariable String roomId) {
        log.info(roomId + " 삭제");
        roomService.destroyChatRoom(roomId);
        return ResponseEntity.noContent().build();
    }


}
