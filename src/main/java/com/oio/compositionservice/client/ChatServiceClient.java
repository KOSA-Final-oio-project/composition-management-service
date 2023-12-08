package com.oio.compositionservice.client;

import com.oio.compositionservice.dto.chat.ChatDto;
import com.oio.compositionservice.dto.chat.ChatRoomDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "chat-service")
public interface ChatServiceClient {


    @GetMapping("/rooms")
    List<ChatRoomDto> findAllChatRoom();
}
