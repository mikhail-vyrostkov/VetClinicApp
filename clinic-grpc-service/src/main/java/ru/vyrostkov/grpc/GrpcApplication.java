package ru.vyrostkov.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GrpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcApplication.class, args);
	}

}
