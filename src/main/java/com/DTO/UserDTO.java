package com.DTO;

import com.validation.Email;
import com.validation.EmailExist;
import com.validation.UserExist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "{blank.error}")
    @Size(message = "{size.error}",min = 4,max = 15)
    @UserExist(message = "{exist.error}")
    private String username;
    @NotBlank(message = "{blank.error}")
    @Email(message = "{Email.error}")
    @EmailExist(message = "{exist.error}")
    private String email;
    @NotBlank(message = "{blank.error}")
    @Size(message = "{size.error}",min = 8,max = 50)
    private String password;

}
