package com.DTO;

import com.validation.Email;
import com.validation.EmailExist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ConfirmEmail {
    @NotBlank(message = "{blank.error}")
    @Email(message = "{Email.error}")
    @EmailExist(message = "{notExist.error}", choice = true)
    String email;
}
