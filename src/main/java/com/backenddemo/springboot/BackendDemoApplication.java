package com.backenddemo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.backenddemo.springboot.model.User;
import com.backenddemo.springboot.repository.UserRepository;

@SpringBootApplication
public class BackendDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendDemoApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	
	//Generate default users
	@Override
	public void run(String... args) throws Exception {
		this.userRepository.save(new User("Koichi", "Sugi", "demo-spring-backend@gmail.com","Jan 23th","Arts","Engineer","Tokyo"));
		this.userRepository.save(new User("Elon", "Musk", "elon-mask@gmail.com","Jan 23th","Arts","Engineer","Tokyo"));
		this.userRepository.save(new User("Bill", "Gates", "bill-gates@gmail.com","Jan 23th","Arts","Engineer","Tokyo"));
	}

}
