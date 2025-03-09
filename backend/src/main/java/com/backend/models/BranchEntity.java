package com.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_branch")
public class BranchEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long id;

    @Column(name = "branch_name")
    private  String branchName;

    @Column(name = "address")
    private  String address;


    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserEntity> user = new HashSet<>();

    @OneToMany(mappedBy = "branch",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderEntity> orders = new HashSet<>();

    @OneToMany(mappedBy = "branch",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<InventoryEntity> inventories = new HashSet<>();
}

