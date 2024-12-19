package com.pyomin.hood.guestbook.service;

import org.springframework.stereotype.Service;

import com.pyomin.hood.guestbook.dto.GuestbookDto;
import com.pyomin.hood.guestbook.entity.Guestbook;
import com.pyomin.hood.guestbook.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Override
    public GuestbookDto write(GuestbookDto guestbookDto) {
        Guestbook guestbook = Guestbook.from(guestbookDto);
        Guestbook writtenGuestbook = guestbookRepository.save(guestbook);        
        return GuestbookDto.from(writtenGuestbook);
    }

    
}
