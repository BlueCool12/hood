package com.pyomin.hood.guestbook.service;

import java.util.List;

import com.pyomin.hood.guestbook.dto.GuestbookDto;

public interface GuestbookService {

    void writeGuestbook(GuestbookDto guestbookDto);
    List<GuestbookDto> getAllGuestbooks();
    void modifyGuestbook(GuestbookDto guestbookDto);    
    void deleteGuestbook(GuestbookDto guestbookDto);
}
