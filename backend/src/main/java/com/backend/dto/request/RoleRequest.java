package com.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Data
public class RoleRequest {

    @NotBlank(message = "role name must be not blank")
    @NotNull(message = "role name must be not null")
    private String roleName;

    private String description;
}
