package com.example.samplecryptowallet;

import com.example.samplecryptowallet.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WalletApplicationTests {

    @Autowired
    UserService userService;

	@Test
	void contextLoads() {
		System.out.println("sd");
	}

//    @Test
//    void userCreate() {
//        User dto = new User();
//        dto.setUsername("camomoooo");
//        userService.create(dto);
//        assert
//    }

}
