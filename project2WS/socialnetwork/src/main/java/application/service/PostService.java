package application.service;

import java.util.List;

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
	
	public List<Post> getAllPost(){
		return dao.findAll();
	}
	
	public void LikePost(Post post, User user) {
		if(!post.getLikers().contains(user))
			post.getLikers().add(user);
		else
			post.getLikers().remove(user);
		
		dao.save(post);
	}
}
