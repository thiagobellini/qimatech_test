package com.technical.qimatech_test.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDTO {
    private UUID idUser;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
