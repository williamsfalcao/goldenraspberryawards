package com.goldenraspberryawards;

import com.goldenraspberryawards.event.EventListner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GoldenraspberryawardsApplication {

	public static void main(String[] args){

		SpringApplication.run(GoldenraspberryawardsApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public EventListner eventListner() {
		return new EventListner();
	}

}
