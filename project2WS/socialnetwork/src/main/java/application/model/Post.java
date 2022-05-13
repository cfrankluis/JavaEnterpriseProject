package application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="post_table")
public class Post {
	
	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="post_body", nullable=false)
	private String postBody;
	
	@Column(name="user_post", nullable=false)
	private int userPosted;

	/**
	 * @param id
	 * @param postBody
	 * @param userPosted
	 */
	public Post( String postBody, int userPosted) {
		super();
		this.postBody = postBody;
		this.userPosted = userPosted;
	}
	
	public String getPostBody() {
		return this.postBody;
	}


	


}
