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

    private GuestbookDto guestbookDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        guestbookDto = new GuestbookDto("author", "password", "message");
    }

    @Test
    void testWriteSuccess() {
        // given
        Guestbook guestbook = new Guestbook(
                1L,
                "author",
                "password",
                "message",
                LocalDateTime.now());

        // when
        when(guestbookRepository.save(any(Guestbook.class))).thenReturn(guestbook);

        // then
        guestbookService.writeGuestbook(guestbookDto);        

        verify(guestbookRepository, times(1)).save(any(Guestbook.class));
    }

    @Test
    void testWriteFailure() {
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
    void testGetAllGuestbooks() {
        // given
        Guestbook guestbook1 = new Guestbook(1L, "author1", "password1", "message1", LocalDateTime.now());
        Guestbook guestbook2 = new Guestbook(2L, "author2", "password2", "message2", LocalDateTime.now());

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
}
