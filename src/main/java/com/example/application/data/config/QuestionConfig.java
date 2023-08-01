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
            Set<Question> two = new HashSet<>();

            Question q1 = new Question("What is your name?",
                    "Bill",
                    "Eric",
                    "Chip",
                    "Ahoy"

            );
            Question q2 = new Question("What is your age?",
                    "12",
                    "23",
                    "45",
                    "20"

            );
            Question q3 = new Question("What is your height?",
                    "6 ft",
                    "5 ft",
                    "5 ft 11 in",
                    "5 ft 4in"

            );
            one.addAll(List.of(q1, q2));
            two.addAll(List.of(q1, q2, q3));

            QuestionSet oneSet = new QuestionSet("Set One", one, one.size());
            QuestionSet twoSet = new QuestionSet("Set two", two, two.size());
            repository.saveAll(List.of(oneSet, twoSet));
        };
    }
}
