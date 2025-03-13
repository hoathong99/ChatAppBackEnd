package com.example.ChatApp.Repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ChatApp.Model.Chat;
import com.example.ChatApp.Model.ChatMember;
import com.example.ChatApp.Model.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByChat(Chat chat);
    List<Message> findBySender(ChatMember sender);
}
