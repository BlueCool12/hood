package com.pyomin.hood.guestbook.dto.request;

import com.pyomin.hood.guestbook.dto.GuestbookDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateGuestbookRequest {
    
    @NotNull(message = "ID는 필수입니다.")
    private Long id;
    
    @NotBlank(message = "작성자 이름은 공백일 수 없습니다.")
    @Size(max = 20, message = "작성자 이름은 최대 20글자입니다.")
    private String author;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Pattern(regexp = "\\d{4}", message = "비밀번호는 4자리 숫자여야 합니다.")
    private String password;

    @NotBlank(message = "메시지는 공백일 수 없습니다.")
    @Size(max = 500, message = "메시지는 최대 500글자입니다.")
    private String message;

    public GuestbookDto toGuestbookDto() {
        return new GuestbookDto(this.id, this.author, this.password, this.message);
    }
}
