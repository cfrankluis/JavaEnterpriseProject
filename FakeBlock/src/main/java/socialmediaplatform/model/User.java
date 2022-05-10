package socialmediaplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name="User")
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="user_first_name", unique=false, nullable=false)
	private String fName;
	
	@Column(name="user_last_name", unique=false, nullable=false)
	private String lName;
	
	@Column(name="user_birthdate", unique=false, nullable=false)
	private String bDate;
	
	@Column(name="user_username", unique=true, nullable=false)
	private String username;
	
	@Column(name="user_password", unique=false, nullable=false)
	private String password;
	
	@Column(name="email_address", unique=true, nullable=false)
	private String email;
	
	@Column(name="biography", unique=false, nullable=true)
	private String bioDesc;

}

