package com.backend.dto.response;

import com.backend.common.Gender;
import com.backend.common.UserStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
    private String userName;
    private String address;
    private Gender gender;
    private UserStatus status;
    private Date birthDay;
    private String imageAvatarUrl;
}
