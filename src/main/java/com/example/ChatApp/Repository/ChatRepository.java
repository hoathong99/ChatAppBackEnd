package com.example.ChatApp.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ChatApp.Model.Chat;
@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID>{

}
