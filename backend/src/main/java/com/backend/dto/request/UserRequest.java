package com.backend.dto.request;

import com.backend.common.Gender;
import com.backend.common.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Data
@Getter
@ToString
public class UserRequest {

    @NotNull(message = "email must be not null")
    @NotBlank(message = "email must be not blank")
    @Email(message =  "email doesn't valid")
    private String email;

    @NotNull(message = "email must be not null")
    private String userName;

    private String address;

    private Gender gender;
    private UserStatus status;
    private Date birthDay;
    private String imageAvatarUrl;


}
