package com.pyomin.hood.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pyomin.hood.guestbook.dto.GuestbookDto;
import com.pyomin.hood.guestbook.entity.Guestbook;
import com.pyomin.hood.guestbook.repository.GuestbookRepository;

public class GuestbookServiceTest {

    @Mock
    private GuestbookRepository guestbookRepository;

    @InjectMocks
    private GuestbookServiceImpl guestbookService;

    private GuestbookDto writeGuestbookDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        writeGuestbookDto = new GuestbookDto("author", "password", "message");
    }

    @Test
    void testWriteSuccess() {
        // given
        Guestbook guestbook = new Guestbook(
            1L,
            "author",
            "password",
            "message",
            LocalDateTime.now()
        );

        when(guestbookRepository.save(any(Guestbook.class))).thenReturn(guestbook);

        // when
        GuestbookDto writtenGuestbookDto = guestbookService.write(writeGuestbookDto);

        // then
        assertNotNull(writtenGuestbookDto);
        assertEquals(guestbook.getId(), writtenGuestbookDto.getId());
        assertEquals(guestbook.getAuthor(), writtenGuestbookDto.getAuthor());
        assertEquals(guestbook.getMessage(), writtenGuestbookDto.getMessage());
        assertEquals(guestbook.getCreatedAt(), writtenGuestbookDto.getCreatedAt());
        verify(guestbookRepository, times(1)).save(any(Guestbook.class));
    }

    @Test
    void testWriteFailure() {
        // given
        when(guestbookRepository.save(any(Guestbook.class)))
            .thenThrow(new RuntimeException("방명록 작성 실패"));

        // when & then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            guestbookService.write(writeGuestbookDto);
        });

        assertEquals("방명록 작성 실패", exception.getMessage());
        verify(guestbookRepository, times(1)).save(any(Guestbook.class));
    }
}
