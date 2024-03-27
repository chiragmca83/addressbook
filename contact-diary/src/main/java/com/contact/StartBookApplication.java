package com.contact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class StartBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartBookApplication.class, args);
    }

    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(AddressBookRepository addressBookrepository) {
        return args -> {
            addressBookrepository.save(new AddressBook("Chirag", "989898"));
            addressBookrepository.save(new AddressBook("Scott", "123123"));
        };
    }
}