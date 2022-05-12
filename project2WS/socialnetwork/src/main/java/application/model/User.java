package application.model;

import java.util.List;

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
@Table(name="user_table")
@JsonIgnoreProperties(value={"posts","comments", "securityQuestions","hibernateLazyInitializer", "handler"}, allowSetters= true)
public class User {
	
	//Required Columns
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="user_name", nullable=false)
	private String username;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	//Reference Objects
	@OneToMany(mappedBy="author", fetch=FetchType.LAZY)
	private List<Post> posts;
	
	@OneToMany(mappedBy="author",fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private List<SecurityAnswer> securityQuestions;

	//INSERT ACCOUNT CONSTRUCTOR
	public User(String firstName, String lastName, String username, String email, String password,
			List<SecurityAnswer> securityQuestions) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.securityQuestions = securityQuestions;
	}
	
	
}
