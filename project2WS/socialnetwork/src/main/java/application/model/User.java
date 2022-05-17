package application.model;

import java.util.List;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user_table")
@JsonIgnoreProperties(value = { "comments", "securityQuestions", "hibernateLazyInitializer",
"handler" }, allowSetters = true)
public class User {
	
	private String page;
	private boolean loggedIn;
	/**
	 * for password reset
	 * @param email
	 */
	public User(String email) {
		super();
		this.email = email;
	}
	
	// Required Columns
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "user_name", unique = true, nullable = false)
	private String username;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "bio", nullable = true)
	private String bio;

	@Column(name = "confirmed")
	private boolean confirmed = false;

	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	private List<Comment> comments;

	// Reference Objects
	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	private List<Post> posts;

	// Session ACCOUNT CONSTRUCTOR
	public User(int id, String firstName, String lastName, String username, String email, String password, String bio) {
		super();
		this.userId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.bio = bio;

	}

	public User(String firstName, String lastName, String username, String email, String password, String bio) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.bio = bio;
	}

	/**
	 * @param username
	 * @param password
	 * @param email
	 */
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return this.userId == other.getUserId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public String toString() {
		return "\tUser [userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", bio=" + bio + ", loggedIn=" + loggedIn
				+ ", confirmed=" + confirmed + ", page=" + page + "]";
	}

	
	



}
