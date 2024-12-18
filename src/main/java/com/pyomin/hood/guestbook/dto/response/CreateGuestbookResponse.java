package com.pyomin.hood.guestbook.dto.response;

import java.time.LocalDateTime;

import com.pyomin.hood.guestbook.dto.WriteGuestbookDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateGuestbookResponse {

    private Long id;
    private String author;
    private String message;
    private LocalDateTime createdAt;
    private boolean success;
    private String statusMessage;

    /**
     * {@link WriteGuestbookDto}를 {@link CreateGuestbookResponse}로 변환합니다.
     * 이 메서드는 제공된 {@link WriteGuestbookDto}와 성공 플래그, 메시지를 사용하여 응답 DTO를 생성합니다.
     *
     * @param writtenDto 작성된 방명록 항목의 세부 정보를 포함하는 {@link WriteGuestbookDto} 객체
     * @param success 작업의 성공 상태를 나타내는 boolean 값
     * @param message 작업 결과에 대한 추가 정보를 제공하는 메시지 (예: 성공 또는 실패 메시지)
     * @return {@link WriteGuestbookDto}의 세부 정보와 성공 상태 및 메시지를 포함하는 {@link CreateGuestbookResponse} 객체
     */
    public static CreateGuestbookResponse of(WriteGuestbookDto writtenDto, boolean success, String message) {
        return new CreateGuestbookResponse(
                writtenDto.getId(),
                writtenDto.getAuthor(),
                writtenDto.getMessage(),
                writtenDto.getCreatedAt(),
                success,
                message);
    }
}
