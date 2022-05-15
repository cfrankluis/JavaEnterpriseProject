package application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.SecurityQuestion;

public interface SecurityQuestionDao extends JpaRepository<SecurityQuestion, Integer> {

}
