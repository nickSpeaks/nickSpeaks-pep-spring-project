package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dto.*;
import com.example.entity.Message;
import com.example.exception.UserNotFoundException;
import com.example.repository.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    public MessageResponse createMessage(CreateMessageRequest messageRequest) {
        accountRepository.findById(messageRequest.getPostedBy())
                            .orElseThrow(UserNotFoundException::new);

        Message message = new Message(
                messageRequest.getPostedBy(), 
                messageRequest.getMessageText(), 
                messageRequest.getTimePostedEpoch());
        Message saved = messageRepository.save(message);
        return new MessageResponse(saved.getMessageId(), saved.getPostedBy(), saved.getMessageText(), saved.getTimePostedEpoch());
    }

    public List<MessageResponse> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(message -> new MessageResponse(
                                    message.getMessageId(),
                                    message.getPostedBy(),
                                    message.getMessageText(),
                                    message.getTimePostedEpoch()))
                                    .collect(Collectors.toList());
    }

    public Optional<MessageResponse> getMessageById(Integer id) {
        return messageRepository
                .findById(id)
                .map(m -> new MessageResponse(
                                m.getMessageId(), 
                                m.getPostedBy(),
                                m.getMessageText(),
                                m.getTimePostedEpoch()));
    }

    public Integer deleteMessageById(Integer id) { return messageRepository.deleteByMessageId(id); }

    public Integer updateMessageById(Integer id, UpdateMessageRequest updateMessageRequest) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isEmpty()) {
            Message message = optionalMessage.get();
            message.setMessageText(updateMessageRequest.getMessageText());
            messageRepository.save(message);
            return 1;
        }
        return 0;
    }

    public List<MessageResponse> getAllMessagesByUser(Integer accountId) {
        return messageRepository.findAllByPostedBy(accountId)
                .stream()
                .map(m -> new MessageResponse(m.getMessageId(), m.getPostedBy(), m.getMessageText(), m.getTimePostedEpoch()))
                .collect(Collectors.toList());
    }
}
