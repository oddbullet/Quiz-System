package com.example.application.data.repository;

import com.example.application.data.entity.QuestionSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSetRepository extends JpaRepository<QuestionSet, Long> {
    @Query("SELECT qs FROM QuestionSet qs LEFT JOIN FETCH qs.questions WHERE qs.name = :name")
    QuestionSet findByName(@Param("name") String name);
}
