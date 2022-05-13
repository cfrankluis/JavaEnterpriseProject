<<<<<<< HEAD

package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.PostDao;
import application.model.Post;

@Service("postService")
public class PostService {

	private PostDao dao;
	
	@Autowired
	public PostService(PostDao dao) {
		this.dao = dao;
	}

	public void createPost(Post post) {
		dao.save(post);
	}

}
>>>>>>> 60526c5cca5aca460121f89d03a2c53f8b054516
