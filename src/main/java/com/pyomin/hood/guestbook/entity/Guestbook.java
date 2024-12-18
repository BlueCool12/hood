package com.pyomin.hood.guestbook.entity;

import java.time.LocalDateTime;

import com.pyomin.hood.guestbook.dto.WriteGuestbookDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Guestbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public static Guestbook from(WriteGuestbookDto dto) {
        return new Guestbook(
            null,
            dto.getAuthor(),
            dto.getPassword(),
            dto.getMessage(),
            LocalDateTime.now()
        );
    }
}
