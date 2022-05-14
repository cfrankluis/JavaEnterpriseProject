package application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Post;
public interface PostDao extends JpaRepository<Post, Integer> {
	
}
