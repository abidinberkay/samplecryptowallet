package com.example.samplecryptowallet.web;

import com.example.samplecryptowallet.Service.TradeService;
import com.example.samplecryptowallet.Service.UserService;
import com.example.samplecryptowallet.model.dto.UserDto;
import com.example.samplecryptowallet.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baraka/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    Mapper mapper;

    @Autowired
    TradeService accountService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(mapper.convertUserToDto(
                userService.create(mapper.convertUserDtoToEntity(userDto))));
    }
}
