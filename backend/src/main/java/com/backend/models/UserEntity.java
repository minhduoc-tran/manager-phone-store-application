package com.backend.models;

import com.backend.common.Gender;
import com.backend.common.UserStatus;
import com.backend.common.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tb_user")
public class UserEntity extends  BaseEntity  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", length = 255)
    private Long userId;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "user_name", length = 255)
    private  String userName;

    @Column(name = "password", length = 255)
    private  String password;

    @Column(name = "address", length = 255)
    private  String address;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "gender", length = 255)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", length = 255)
    private UserStatus status;

    @Column(name = "date_of_birth", length = 255)
    private Date birthDay;

    @Column(name = "image_avatar_url", length = 255)
    private  String imageAvatarUrl;
}


