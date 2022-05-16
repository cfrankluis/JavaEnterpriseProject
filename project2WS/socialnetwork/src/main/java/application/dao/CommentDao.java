package application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Comment;
import application.model.Post;

public interface CommentDao extends JpaRepository<Comment, Integer> {
	public List<Comment> findByPost(Post post);

}
