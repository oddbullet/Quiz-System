package com.example.application.data.config;

import com.example.application.data.entity.Question;
import com.example.application.data.entity.QuestionSet;
import com.example.application.data.repository.QuestionSetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class QuestionConfig {


    @Bean
    CommandLineRunner commandLineRunner (QuestionSetRepository repository) {
        return args -> {
            System.out.println("Command line running...");
            Set<Question> one = new HashSet<>();
            Question q1 = new Question("What is your name?",
                    "Bill",
                    "Eric",
                    "Chip",
                    "Ahoy"

            );
            one.add(q1);
            QuestionSet oneSet = new QuestionSet("Set One", one, one.size());
            repository.save(oneSet);
        };
    }
}
