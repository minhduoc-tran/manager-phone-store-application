package com.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract  class BaseEntity {

    @Column(name = "created_at", length = 255)
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date createdAt;


    @Column(name = "updated_at", length = 255)
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private  Date updatedAt;
}
