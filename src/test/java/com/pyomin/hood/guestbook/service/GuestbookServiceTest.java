package com.pyomin.hood.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pyomin.hood.guestbook.dto.GuestbookDto;
import com.pyomin.hood.guestbook.entity.Guestbook;
import com.pyomin.hood.guestbook.exception.GuestbookNotFoundException;
import com.pyomin.hood.guestbook.exception.GuestbookPasswordMismatchException;
import com.pyomin.hood.guestbook.repository.GuestbookRepository;

public class GuestbookServiceTest {

    @Mock
    private GuestbookRepository guestbookRepository;

    @InjectMocks
    private GuestbookServiceImpl guestbookService;

    private GuestbookDto guestbookDto;
    private Guestbook guestbook;

    @BeforeEach
    void 테스트_준비() {
        MockitoAnnotations.openMocks(this);
        guestbook = new Guestbook(1L, "작성자", "1234", "메시지", LocalDateTime.now());
        guestbookDto = new GuestbookDto(1L, "작성자", "1234", "메시지", LocalDateTime.now());
    }

    @Test
    void 방명록_작성_성공() {
        // given
        when(guestbookRepository.save(any(Guestbook.class))).thenReturn(guestbook);

        // when
        guestbookService.writeGuestbook(guestbookDto);

        // then
        verify(guestbookRepository, times(1)).save(any(Guestbook.class));
    }

    @Test
    void 방명록_작성_실패() {
        // given
        when(guestbookRepository.save(any(Guestbook.class)))
                .thenThrow(new RuntimeException("방명록 작성 실패"));

        // when & then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            guestbookService.writeGuestbook(guestbookDto);
        });

        assertEquals("방명록 작성 실패", exception.getMessage());
        verify(guestbookRepository, times(1)).save(any(Guestbook.class));
    }

    @Test
    void 방명록_목록_조회_성공() {
        // given
        Guestbook guestbook1 = new Guestbook(1L, "author1", "1234", "message1", LocalDateTime.now());
        Guestbook guestbook2 = new Guestbook(2L, "author2", "4321", "message2", LocalDateTime.now());
        when(guestbookRepository.findAll()).thenReturn(Arrays.asList(guestbook1, guestbook2));

        // when
        List<GuestbookDto> guestbookDtos = guestbookService.getAllGuestbooks();

        // then
        assertNotNull(guestbookDtos);
        assertEquals(2, guestbookDtos.size());

        GuestbookDto dto1 = guestbookDtos.get(0);
        assertEquals(guestbook1.getAuthor(), dto1.getAuthor());
        assertEquals(guestbook1.getMessage(), dto1.getMessage());

        GuestbookDto dto2 = guestbookDtos.get(1);
        assertEquals(guestbook2.getAuthor(), dto2.getAuthor());
        assertEquals(guestbook2.getMessage(), dto2.getMessage());

        verify(guestbookRepository, times(1)).findAll();
    }

    @Test
    void 방명록_수정_성공() {
        // given
        GuestbookDto newGuestbookDto = new GuestbookDto(1L, "newAuthor", "1234", "newMessage");
        when(guestbookRepository.findById(1L)).thenReturn(Optional.of(guestbook));

        // when
        guestbookService.modifyGuestbook(newGuestbookDto);

        // then
        assertEquals("newAuthor", guestbook.getAuthor());
        assertEquals("newMessage", guestbook.getMessage());

        verify(guestbookRepository, times(1)).findById(1L);
    }

    @Test
    void 방명록_삭제_성공() {
        // given
        when(guestbookRepository.findById(1L)).thenReturn(Optional.of(guestbook));

        // when
        guestbookService.deleteGuestbook(guestbookDto);

        // then
        verify(guestbookRepository, times(1)).findById(1L);
        verify(guestbookRepository, times(1)).deleteById(1L);
    }

    @Test
    void 방명록_삭제_방명록_없음() {
        // given
        when(guestbookRepository.findById(1L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(GuestbookNotFoundException.class,
                () -> guestbookService.deleteGuestbook(guestbookDto));
    }

    @Test
    void 방명록_삭제_비밀번호_불일치() {
        // given
        GuestbookDto passwordMismatchDto = new GuestbookDto(1L, "4321");
        when(guestbookRepository.findById(1L)).thenReturn(Optional.of(guestbook));

        // when & then
        assertThrows(GuestbookPasswordMismatchException.class,
                () -> guestbookService.deleteGuestbook(passwordMismatchDto));
    }

}
