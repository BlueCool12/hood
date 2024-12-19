package com.pyomin.hood.guestbook.entity;

import java.time.LocalDateTime;

import com.pyomin.hood.guestbook.dto.GuestbookDto;

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

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public static Guestbook from(GuestbookDto dto) {
        return new Guestbook(
            null,
            dto.getAuthor(),
            dto.getPassword(),
            dto.getMessage(),
            LocalDateTime.now()
        );
    }
}
