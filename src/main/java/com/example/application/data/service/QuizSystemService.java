package com.example.application.data.service;

import com.example.application.data.entity.Question;
import com.example.application.data.entity.QuestionSet;
import com.example.application.data.repository.QuestionRepository;
import com.example.application.data.repository.QuestionSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizSystemService {

    private final QuestionRepository questionRepository;
    private final QuestionSetRepository questionSetRepository;

    @Autowired
    public QuizSystemService(QuestionRepository questionRepository, QuestionSetRepository questionSetRepository) {
        this.questionRepository = questionRepository;
        this.questionSetRepository = questionSetRepository;
    }

    public void saveSet(QuestionSet set) {
        if(set == null) {
            System.out.println("Empty set is not allowed!");
            return;
        }
        questionSetRepository.save(set);
    }

    public void deleteSet(QuestionSet set) {
        questionSetRepository.delete(set);
    }

    public List<QuestionSet> findAllSet() {
        return questionSetRepository.findAll();
    }
}
