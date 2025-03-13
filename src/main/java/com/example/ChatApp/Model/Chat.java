package com.example.ChatApp.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String chatName;

    @Column(nullable = false)
    private Boolean isGroupChat;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "chat")
    private List<ChatMember> chatMembers;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the chatName
	 */
	public String getChatName() {
		return chatName;
	}

	/**
	 * @param chatName the chatName to set
	 */
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	/**
	 * @return the isGroupChat
	 */
	public Boolean getIsGroupChat() {
		return isGroupChat;
	}

	/**
	 * @param isGroupChat the isGroupChat to set
	 */
	public void setIsGroupChat(Boolean isGroupChat) {
		this.isGroupChat = isGroupChat;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the chatMembers
	 */
	public List<ChatMember> getChatMembers() {
		return chatMembers;
	}

	/**
	 * @param chatMembers the chatMembers to set
	 */
	public void setChatMembers(List<ChatMember> chatMembers) {
		this.chatMembers = chatMembers;
	}

	/**
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", chatName=" + chatName + ", isGroupChat=" + isGroupChat + ", createdAt=" + createdAt
				+ ", chatMembers=" + chatMembers + ", messages=" + messages + "]";
	}
    
	
}
