package com.pyomin.hood.guestbook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pyomin.hood.guestbook.dto.WriteGuestbookDto;
import com.pyomin.hood.guestbook.dto.request.CreateGuestbookRequest;
import com.pyomin.hood.guestbook.dto.response.CreateGuestbookResponse;
import com.pyomin.hood.guestbook.service.GuestbookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// 자바 docs 표준
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guestbooks")
public class GuestbookController {

    private final GuestbookService guestbookService;

    @PostMapping
    public ResponseEntity<CreateGuestbookResponse> createGuestbook(@Valid @RequestBody CreateGuestbookRequest request) {
            WriteGuestbookDto guestbookDto = WriteGuestbookDto.from(request);
            WriteGuestbookDto writtenGuestbookDto = guestbookService.write(guestbookDto);
            CreateGuestbookResponse response = CreateGuestbookResponse.of(writtenGuestbookDto, true, "방명록이 성공적으로 작성되었습니다.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public void getAllGuestbooks() {
        
    }

}
