package application.model;

import java.util.Date;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="comment_table")
@JsonIgnoreProperties(value={"post","likers","hibernateLazyInitializer", "handler"}, allowSetters= true)
public class Comment {
	
	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="comment_content", nullable=false)
	private String content;
		
	//@Column(name="date_created", nullable=false)
	@CreationTimestamp
	private Date dateCreated;
	
	@JsonIgnoreProperties(value={"username","password","email","hibernateLazyInitializer", "handler"}, allowSetters= true)
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="user_FK")
	private User author;
	
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="post_FK")
	private Post post;
	
	@ManyToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="liker_FK")
	private Set<User> likers;
	
	@Transient
	private int numOfLikes;
	
	public int getNumOfLikes() {
		return likers == null ? 0 : likers.size();
	}

	//INSERT COMMENTS CONSTRUCTOR
	public Comment(String content, User author, Post post) {
		this.content = content;
		this.author = author;
		this.post = post;
		this.dateCreated = new Date();
	}
}
