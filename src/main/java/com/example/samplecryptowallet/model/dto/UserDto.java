package com.example.samplecryptowallet.model.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    @NotNull
    private String username;

}
