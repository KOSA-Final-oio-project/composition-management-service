package com.oio.compositionservice.controller;

import com.oio.compositionservice.client.ChatServiceClient;
import com.oio.compositionservice.dto.chat.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oio")
public class ChatController {

    private final ChatServiceClient chatServiceClient;

    @GetMapping("/rooms")
    public List<ChatRoomDto> chatRoom() {
        return chatServiceClient.findAllChatRoom();
    }
}
