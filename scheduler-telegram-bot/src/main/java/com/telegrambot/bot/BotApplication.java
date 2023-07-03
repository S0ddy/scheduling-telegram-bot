package com.telegrambot.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class BotApplication {

	public static void main(String[] args)  {
		SpringApplication.run(BotApplication.class, args);
		System.out.println("run");

	}


}
