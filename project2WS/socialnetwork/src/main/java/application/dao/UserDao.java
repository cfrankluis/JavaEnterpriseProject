<<<<<<< HEAD
package application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.User;

public interface UserDao extends JpaRepository<User, Integer>{
	public User findByEmail(String email);
	public User findByUsername(String username);
}

>>>>>>> 60526c5cca5aca460121f89d03a2c53f8b054516
