package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import topgoal.tube.model.ChatRoom;
import topgoal.tube.repository.ChatRoomRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Slf4j
public class ChatRoomController {

    private final ChatRoomRepository repository;

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam String uuid, RedirectAttributes redirectAttributes) {
        log.info("Create New Room, admin : " + uuid);
        redirectAttributes.addFlashAttribute("roomName", repository.createChatRoom(uuid));
        return "redirect:/chat/enter";
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return repository.findRoomById(roomId);
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(String roomId, Model model) {
        log.info("get Chat Room, roomID : " + roomId);
        model.addAttribute("room", repository.findRoomById(roomId));
    }
}
