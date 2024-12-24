package com.pyomin.hood.guestbook.dto.response;

import java.util.List;

import com.pyomin.hood.guestbook.dto.GuestbookDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AllGuestbooksResponse {
    private final List<GuestbookDto> guestbooks;
}
