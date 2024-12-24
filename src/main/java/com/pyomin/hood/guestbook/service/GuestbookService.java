package com.pyomin.hood.guestbook.service;

import java.util.List;

import com.pyomin.hood.guestbook.dto.GuestbookDto;

public interface GuestbookService {

    void writeGuestbook(GuestbookDto dto);
    List<GuestbookDto> getAllGuestbooks();
    void modifyGuestbook(GuestbookDto dto);
}
