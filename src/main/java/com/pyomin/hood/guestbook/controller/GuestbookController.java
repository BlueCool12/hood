package com.pyomin.hood.guestbook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pyomin.hood.guestbook.dto.GuestbookDto;
import com.pyomin.hood.guestbook.dto.request.CreateGuestbookRequest;
import com.pyomin.hood.guestbook.dto.response.CreateGuestbookResponse;
import com.pyomin.hood.guestbook.service.GuestbookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guestbooks")
public class GuestbookController {

    private final GuestbookService guestbookService;

    // Null 안정성 처리

    @PostMapping
    public ResponseEntity<CreateGuestbookResponse> createGuestbook(@Valid @RequestBody CreateGuestbookRequest request) {
            GuestbookDto guestbookDto = GuestbookDto.from(request);
            GuestbookDto writtenGuestbookDto = guestbookService.write(guestbookDto);
            CreateGuestbookResponse response = CreateGuestbookResponse.of(writtenGuestbookDto, true, "방명록이 성공적으로 작성되었습니다.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
