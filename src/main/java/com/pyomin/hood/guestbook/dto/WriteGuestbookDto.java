package com.pyomin.hood.guestbook.dto;

import java.time.LocalDateTime;

import com.pyomin.hood.guestbook.dto.request.CreateGuestbookRequest;
import com.pyomin.hood.guestbook.entity.Guestbook;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriteGuestbookDto {

    private Long id;
    private String author;
    private String password;
    private String message;
    private LocalDateTime createdAt;

    public WriteGuestbookDto(Long id, String author, String message, LocalDateTime createdAt) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.createdAt = createdAt;
    }

    public WriteGuestbookDto(String author, String password, String message) {
        this.author = author;
        this.password = password;
        this.message = message;
    }

    public static WriteGuestbookDto from(CreateGuestbookRequest request) {
        return new WriteGuestbookDto(
                request.getAuthor(),
                request.getPassword(),
                request.getMessage());
    }

    public static WriteGuestbookDto from(Guestbook guestbook) {
        return new WriteGuestbookDto(
                guestbook.getId(),
                guestbook.getAuthor(),
                guestbook.getMessage(),
                guestbook.getCreatedAt());
                
    }
}
