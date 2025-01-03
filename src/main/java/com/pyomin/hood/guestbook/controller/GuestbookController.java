package com.pyomin.hood.guestbook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pyomin.hood.common.dto.ResponseWrapper;
import com.pyomin.hood.guestbook.dto.GuestbookDto;
import com.pyomin.hood.guestbook.dto.request.CreateGuestbookRequest;
import com.pyomin.hood.guestbook.dto.request.DeleteGuestbookRequest;
import com.pyomin.hood.guestbook.dto.request.UpdateGuestbookRequest;
import com.pyomin.hood.guestbook.dto.response.AllGuestbooksResponse;
import com.pyomin.hood.guestbook.service.GuestbookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guestbooks")
public class GuestbookController {

    private final GuestbookService guestbookService;

    @PostMapping
    public ResponseWrapper<Void> createGuestbook(
            @Valid @RequestBody CreateGuestbookRequest request) {

        GuestbookDto guestbookDto = request.toGuestbookDto();
        guestbookService.writeGuestbook(guestbookDto);

        return ResponseWrapper.<Void>builder()
                .success(true)
                .message("방명록이 성공적으로 작성되었습니다.")
                .build();
    }

    @GetMapping
    public ResponseWrapper<AllGuestbooksResponse> getAllGuestbooks() {
        AllGuestbooksResponse guestbooks = new AllGuestbooksResponse(guestbookService.getAllGuestbooks());

        return ResponseWrapper.<AllGuestbooksResponse>builder()
                .success(true)
                .message("모든 방명록을 성공적으로 불러왔습니다.")
                .data(guestbooks)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseWrapper<Void> updateGuestbook(@PathVariable("id") Long id,
            @Valid @RequestBody UpdateGuestbookRequest request) {

        request.setId(id);
        GuestbookDto guestbookDto = request.toGuestbookDto();
        guestbookService.modifyGuestbook(guestbookDto);

        return ResponseWrapper.<Void>builder()
                .success(true)
                .message("방명록이 성공적으로 수정되었습니다.")
                .build();
    }

    @DeleteMapping
    public ResponseWrapper<Void> deleteGuestbook(@PathVariable("id") Long id,
            @Valid @RequestBody DeleteGuestbookRequest request) {

        request.setId(id);
        GuestbookDto guestbookDto = request.toGuestbookDto();
        guestbookService.deleteGuestbook(guestbookDto);

        return ResponseWrapper.<Void>builder()
                .success(true)
                .message("방명록이 성공적으로 삭제되었습니다.")
                .build();
    }
}
