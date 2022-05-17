package application.model;

import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "post_table")
@JsonIgnoreProperties(value = { "comments", "likers", "hibernateLazyInitializer", "handler" }, allowSetters = true)
public class Post {

	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;

	@Column(name = "post_content", nullable = false)
	private String content;

	@Column(name = "post_img", nullable = true)
	private String img;

	@Column(name = "date_created", nullable = false)
	private Date dateCreated;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_FK")
	private User author;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<Comment> comments;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "liker_FK")
	private List<User> likers;

	@Transient
	private int numOfLikes;

	public int getNumOfLikes() {
		return likers == null ? 0 : likers.size();
	}

	// INSERT POST CONSTRUCTOR
	public Post(String content, String img, User author) {
		this.content = content;
		this.img = img;
		this.author = author;
		this.dateCreated = new Date();
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", author=" + author.getUsername() + "]";
	}
	
	
}