package socialmediaplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "Comment")
public class Comment {


	@Id
	@Column(name = "comment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	
	@Column(name = "comment_content", nullable=false)
	private String commentContents;

	@Column(name = "comment_date")
	private String datePosted;

	@ManyToOne
	@Column(name = "user_id")
	private User user;
	
	@ManyToOne
	@Column(name = "post_id")
	private int postedTo;
	
	
}
