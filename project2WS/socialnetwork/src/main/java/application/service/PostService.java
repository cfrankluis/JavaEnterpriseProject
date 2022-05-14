package application.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.PostDao;
import application.model.Post;
import application.model.User;

@Service("postService")
public class PostService {
	private PostDao dao;
	
	@Autowired
	public PostService(PostDao dao) {
		this.dao = dao;
	}
	
	/**
	 * @author Frank Carag
	 * @return
	 * A list of all posts in the database.
	 */
	public List<Post> getAllPost(){
		return dao.findAll();
	}
	
	/**
	 * Adds the user to the list of likers in a post and saves
	 * changes to the database
	 * @author Frank Carag
	 */
	public Post likePost(Post post, User user) {
		Set<User> likers = post.getLikers();

		if(likers.contains(user)) {
			likers.remove(user);
		} else {
			likers.add(user);
		}
			
		post.setLikers(likers);
			
		return dao.save(post);
	}
	
	/**
	 * Uses the given id parameter and returns
	 * a post with the given id
	 * @author Frank Carag
	 * @return
	 * A post object with the given id
	 */
	public Post getPostById(int id) {
		if(dao.existsById(id))
			return dao.getById(id);
		else
			return null;
	}
}
