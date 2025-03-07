package com.backend.models;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "tb_role_has_permission")
public class RoleHasPermission {

    @ManyToOne
    @JoinColumn(name = "role_id")
    private  RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private PermissionEntity permission;
}
