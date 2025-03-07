package com.backend.models;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_permission")
public class PermissionEntity  extends BaseEntity implements Serializable {

    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(name = "name")
    private String name;

    @Column(name = "method")
    private String method;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "permission", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RoleHasPermission> roles = new HashSet<>();

}
