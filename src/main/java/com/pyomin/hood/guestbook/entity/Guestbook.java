package com.pyomin.hood.guestbook.entity;

import java.time.LocalDateTime;

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

    @Column(nullable = false, length = 4)
    private String password;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public void changeAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("작성자 이름은 공백일 수 없습니다.");
        }

        if (author.length() > 20) {
            throw new IllegalArgumentException("작성자 이름은 최대 20글자입니다.");
        }

        this.author = author;
    }

    public void changeMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("메시지는 공백일 수 없습니다.");
        }

        if (message.length() > 500) {
            throw new IllegalArgumentException("메시지는 최대 500글자입니다.");
        }

        this.message = message;
    }

}
