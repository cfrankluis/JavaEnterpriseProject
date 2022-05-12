package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.PostDao;

@Service("postService")
public class PostService {
	private PostDao dao;
	
	@Autowired
	public PostService(PostDao dao) {
		this.dao = dao;
	}
}
