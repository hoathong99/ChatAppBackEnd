package com.example.ChatApp.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
	@Getter
    private UUID id;

    @Column(nullable = false, unique = true)
	@Getter @Setter
    private String username;

    @Column(nullable = false, unique = true)
	@Getter @Setter
    private String email;

    @Column(nullable = false)
	@Getter @Setter
    private String passwordHash; // Store hashed password

    @Column(nullable = false)
	@Getter @Setter
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user")
	@Getter @Setter
    private List<ChatMember> chatMembers;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
	@Getter @Setter
    private Role role;  // Add role field
//
//	/**
//	 * @return the id
//	 */
//	public UUID getId() {
//		return id;
//	}
//
//	/**
//	 * @return the username
//	 */
//	public String getUsername() {
//		return username;
//	}
//
//	/**
//	 * @param username the username to set
//	 */
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	/**
//	 * @return the email
//	 */
//	public String getEmail() {
//		return email;
//	}
//
//	/**
//	 * @param email the email to set
//	 */
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	/**
//	 * @return the passwordHash
//	 */
//	public String getPasswordHash() {
//		return passwordHash;
//	}
//
//	/**
//	 * @param passwordHash the passwordHash to set
//	 */
//	public void setPasswordHash(String passwordHash) {
//		this.passwordHash = passwordHash;
//	}
//
//	/**
//	 * @return the createdAt
//	 */
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	/**
//	 * @param createdAt the createdAt to set
//	 */
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	/**
//	 * @return the chatMembers
//	 */
//	public List<ChatMember> getChatMembers() {
//		return chatMembers;
//	}
//
//	/**
//	 * @param chatMembers the chatMembers to set
//	 */
//	public void setChatMembers(List<ChatMember> chatMembers) {
//		this.chatMembers = chatMembers;
//	}
//
//	/**
//	 * @return the role
//	 */
//	public Role getRole() {
//		return role;
//	}
//
//	/**
//	 * @param role the role to set
//	 */
//	public void setRole(Role role) {
//		this.role = role;
//	}

	// DEFAULT TO USER ROLE
	public AppUser(String username, String email, String passwordHash) {
		super();
		this.username = username;
		this.email = email;
		this.passwordHash = passwordHash;
		this.role = Role.USER;
	}
}
