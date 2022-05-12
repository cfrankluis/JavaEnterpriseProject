package application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.SecurityAnswer;

public interface SecurityAnswerDao extends JpaRepository<SecurityAnswer, Integer> {

}
