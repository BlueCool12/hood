package com.pyomin.hood.guestbook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pyomin.hood.common.exception.ErrorCode;
import com.pyomin.hood.guestbook.dto.GuestbookDto;
import com.pyomin.hood.guestbook.entity.Guestbook;
import com.pyomin.hood.guestbook.exception.GuestbookNotFoundException;
import com.pyomin.hood.guestbook.exception.GuestbookPasswordMismatchException;
import com.pyomin.hood.guestbook.repository.GuestbookRepository;

import jakarta.transaction.Transactional;
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
    @Transactional
    public void modifyGuestbook(GuestbookDto newGuestbookDto) {
        Guestbook oldGuestbook = guestbookRepository.findById(newGuestbookDto.getId())
                .orElseThrow(() -> new GuestbookNotFoundException(ErrorCode.GUESTBOOK_NOT_FOUND));

        validatePassword(oldGuestbook, newGuestbookDto);
        updateGuestbookDetails(oldGuestbook, newGuestbookDto);
    }

    private void validatePassword(Guestbook oldGuestbook, GuestbookDto newGuestbookDto) {
        if (!oldGuestbook.getPassword().equals(newGuestbookDto.getPassword())) {
            throw new GuestbookPasswordMismatchException(ErrorCode.GUESTBOOK_PASSWORD_MISMATCH);
        }
    }

    private void updateGuestbookDetails(Guestbook oldGuestbook, GuestbookDto newGuestbookDto) {
        oldGuestbook.changeAuthor(newGuestbookDto.getAuthor());
        oldGuestbook.changeMessage(newGuestbookDto.getMessage());
    }

    @Override
    public void deleteGuestbook() {
        guestbookRepository.deleteById(null);
    }

}
