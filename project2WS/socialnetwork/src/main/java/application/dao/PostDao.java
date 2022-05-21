package application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Post;
import application.model.User;
public interface PostDao extends JpaRepository<Post, Integer> {
	public List<Post> findAllByAuthor(User user);
}
