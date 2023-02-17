package com.accenture.asiggnment;

import com.accenture.asiggnment.model.Users;
import com.accenture.asiggnment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AsiggnmentApplication implements CommandLineRunner {

	@Autowired
	UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(AsiggnmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Users users=new Users();
//		users.setSsn("0000000000002945");
//		users.setFirst_name("Jon");
//		users.setMiddle_name("last_name");
//		users.setBirth_date(new Date());
//		userService.addUser(users);
	}
}
