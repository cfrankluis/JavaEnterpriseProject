package application.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="post_table")
@JsonIgnoreProperties(value={"comments","hibernateLazyInitializer", "handler"}, allowSetters= true)
public class Post {
	
	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="post_content", nullable=false)
	private String content;
		
	@Column(name="date_created", nullable=false)
	private Date dateCreated;
	
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="user_FK")
	private User author;
	
	@OneToMany(mappedBy="post",fetch=FetchType.LAZY)
	private List<Comment> comments;
		
	@ManyToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="liker_FK")
	private List<User> likers;

	//INSERT POST CONSTRUCTOR
	public Post(String content, User author) {
		this.content = content;
		this.author = author;
		this.dateCreated = new Date();
	}
}