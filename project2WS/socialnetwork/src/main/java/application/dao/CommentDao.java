package application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {

}
