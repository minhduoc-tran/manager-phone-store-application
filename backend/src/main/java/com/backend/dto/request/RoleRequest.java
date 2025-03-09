package com.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RoleRequest {
    @NotNull(message = "role name must be not null")
    private String roleName;

    private String description;
}
