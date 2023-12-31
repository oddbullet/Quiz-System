package com.example.application.data.service;

import com.example.application.data.entity.QuestionSet;
import com.example.application.data.repository.QuestionRepository;
import com.example.application.data.repository.QuestionSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public QuestionSet findSetByName(String name) {return questionSetRepository.findByName(name);}

    public Optional<QuestionSet> findSetById(Long id) {return  questionSetRepository.findById(id);}

    public List<QuestionSet> findAllSet() {
        return questionSetRepository.findAll();
    }

    public List<QuestionSet> findAllQuestionSet(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return questionSetRepository.findAll();
        } else {
            return questionSetRepository.searchByName(stringFilter);
        }
    }
}
