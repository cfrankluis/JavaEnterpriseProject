package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.CommentDao;

@Service("commentService")
public class CommentService {
	private CommentDao dao;
	
	@Autowired
	public CommentService(CommentDao dao) {
		this.dao = dao;
	}
}
