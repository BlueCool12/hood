package com.pyomin.hood.guestbook.dto;

import java.time.LocalDateTime;
import com.pyomin.hood.guestbook.entity.Guestbook;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GuestbookDto {

    private Long id;
    private String author;
    private String password;
    private String message;
    private LocalDateTime createdAt;

    public GuestbookDto(Long id, String author, String message, LocalDateTime createdAt) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.createdAt = createdAt;
    }

    public GuestbookDto(Long id, String author, String password, String message) {
        this.id = id;
        this.author = author;
        this.password = password;
        this.message = message;
    }

    public GuestbookDto(String author, String password, String message) {
        this.author = author;
        this.password = password;
        this.message = message;
    }

    public GuestbookDto(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public static GuestbookDto from(Guestbook guestbook) {
        return new GuestbookDto(
                guestbook.getId(),
                guestbook.getAuthor(),
                guestbook.getMessage(),
                guestbook.getCreatedAt());
    }

    public Guestbook toGuestbook() {
        return new Guestbook(
                this.id,
                this.author,
                this.password,
                this.message,
                this.createdAt != null ? this.createdAt : LocalDateTime.now());
    }
}
