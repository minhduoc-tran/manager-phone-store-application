package com.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class BranchRequest implements Serializable {

    @NotBlank(message = "branch name must be not blank")
    private String branchName;

    @NotBlank(message = "address must be not blank")
    private String address;

}
