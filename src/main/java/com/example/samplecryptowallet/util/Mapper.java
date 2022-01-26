package com.example.samplecryptowallet.util;

import com.example.samplecryptowallet.model.Entity.User;
import com.example.samplecryptowallet.model.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    ModelMapper modelMapper;

    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User convertUserDtoToEntity(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        return modelMapper.map(dto, User.class);
    }



}
