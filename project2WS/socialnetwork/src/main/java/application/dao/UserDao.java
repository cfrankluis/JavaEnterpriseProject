package application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.User;

public interface UserDao extends JpaRepository<User, Integer>{
	public User findByEmail(String email);
}
