package com.example.controller;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.*;
import com.example.service.*;

import lombok.RequiredArgsConstructor;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequiredArgsConstructor
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest) {
        return ResponseEntity.ok(accountService.createAccount(createAccountRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AccountResponse> login(@RequestBody @Valid LoginAccountRequest loginAccountRequest) {
        return ResponseEntity.ok(accountService.login(loginAccountRequest));
    }

    @PostMapping("/messages")
    public ResponseEntity<MessageResponse> createMessage(@RequestBody @Valid CreateMessageRequest messageRequest) {
        return ResponseEntity.ok(messageService.createMessage(messageRequest));
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageResponse>> getMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<MessageResponse> getMessage(@PathVariable Integer id) {
        Optional<MessageResponse> message = messageService.getMessageById(id);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok().build());
    }

    @DeleteMapping("messages/{id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer id) {
        Integer response = messageService.deleteMessageById(id);
        return response == 0 ? ResponseEntity.ok().build() : ResponseEntity.ok(response);
    }

    @PatchMapping("/messages/{id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer id,
                                                    @RequestBody @Valid UpdateMessageRequest updateMessageRequest) {
                return ResponseEntity.ok(messageService.updateMessageById(id, updateMessageRequest));
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<MessageResponse>> getMessageByUser(@PathVariable Integer accountId) {
        return ResponseEntity.ok(messageService.getAllMessagesByUser(accountId));
    }
}
