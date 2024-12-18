package com.pyomin.hood.guestbook.service;

import com.pyomin.hood.guestbook.dto.WriteGuestbookDto;

public interface GuestbookService {

    WriteGuestbookDto write(WriteGuestbookDto dto);    
}
