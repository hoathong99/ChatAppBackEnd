package com.example.ChatApp.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ChatApp.Model.AppUser;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID>{
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);
}
