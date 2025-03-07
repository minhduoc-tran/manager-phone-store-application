package com.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_user_has_role")
public class UserHasRole extends  BaseEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private  UserEntity user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private  RoleEntity role;
}
