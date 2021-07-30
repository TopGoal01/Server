package topgoal.tube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import topgoal.tube.model.ChatRoomDTO;
import topgoal.tube.repository.ChatRoomRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Slf4j
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    //채팅방 개설
    @PostMapping(value = "/room")
    @Transactional
    public String create(@RequestParam String admin, RedirectAttributes redirectAttributes) throws Exception {
        log.info("Create New Room, admin : " + admin);
        String roomId = UUID.randomUUID().toString();
        LocalDateTime createdDate = LocalDateTime.now();
        String adminId = admin;

        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
        chatRoomDTO.setRoomId(roomId);
        chatRoomDTO.setCreatedDate(createdDate);
        chatRoomDTO.setAdminId(adminId);

        chatRoomRepository.save(chatRoomDTO);

        redirectAttributes.addFlashAttribute("roomId",  roomId);
        return "redirect:/chat/enter";
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(String roomId, Model model) {
        log.info("get Chat Room, roomID : " + roomId);
        model.addAttribute("room", chatRoomRepository.findById(roomId));
    }
}
