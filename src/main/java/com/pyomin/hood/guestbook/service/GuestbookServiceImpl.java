package com.pyomin.hood.guestbook.service;

import java.util.List;
import java.util.stream.Collectors;

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
    public void writeGuestbook(GuestbookDto guestbookDto) {
        Guestbook guestbook = guestbookDto.toGuestbook();
        guestbookRepository.save(guestbook);
    }

    @Override
    public List<GuestbookDto> getAllGuestbooks() {
        List<Guestbook> guestbooks = guestbookRepository.findAll();
        return guestbooks.stream()
                .map(GuestbookDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void modifyGuestbook(GuestbookDto guestbookDto) {
        Guestbook guestbookToModify = guestbookDto.toGuestbook();
        guestbookRepository.updateGuestbook(guestbookToModify);
    }

}
