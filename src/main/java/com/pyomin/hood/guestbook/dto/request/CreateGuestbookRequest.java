package com.pyomin.hood.guestbook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGuestbookRequest {

    @NotBlank(message = "작성자 이름은 공백일 수 없습니다.")
    @Size(max = 20, message = "작성자 이름은 최대 20글자입니다.")
    private String author;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Size(min = 4, message = "비밀번호는 최소 4글자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "메시지는 공백일 수 없습니다.")
    @Size(max = 500, message = "메시지는 최대 500글자입니다.")
    private String message;


}
