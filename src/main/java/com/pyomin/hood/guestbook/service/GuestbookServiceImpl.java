package com.pyomin.hood.guestbook.service;

import org.springframework.stereotype.Service;

import com.pyomin.hood.guestbook.dto.WriteGuestbookDto;
import com.pyomin.hood.guestbook.entity.Guestbook;
import com.pyomin.hood.guestbook.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Override
    public WriteGuestbookDto write(WriteGuestbookDto guestbookDto) {
        Guestbook guestbook = Guestbook.from(guestbookDto);
        Guestbook writtenGuestbook = guestbookRepository.save(guestbook);
        return WriteGuestbookDto.from(writtenGuestbook);
    }

}
