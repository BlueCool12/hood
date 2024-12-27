package com.pyomin.hood.guestbook.dto.request;

import com.pyomin.hood.guestbook.dto.GuestbookDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteGuestbookRequest {

    @NotNull(message = "ID는 필수입니다.")
    private Long id;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Pattern(regexp = "\\d{4}", message = "비밀번호는 4자리 숫자여야 합니다.")
    private String password;

    public GuestbookDto toGuestbookDto() {
        return new GuestbookDto(this.id, this.password);
    }
}
