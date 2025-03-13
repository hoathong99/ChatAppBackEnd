package com.example.ChatApp.Repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ChatApp.Model.AppUser;
import com.example.ChatApp.Model.Chat;
import com.example.ChatApp.Model.ChatMember;
@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, UUID>{
    List<ChatMember> findByUser(AppUser user);
    List<ChatMember> findByChat(Chat chat);
}
