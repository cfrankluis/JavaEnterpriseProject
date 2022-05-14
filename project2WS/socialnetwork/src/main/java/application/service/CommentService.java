package application.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.CommentDao;
import application.model.Comment;
import application.model.User;

@Service("commentService")
public class CommentService {
	private CommentDao dao;
	
	@Autowired
	public CommentService(CommentDao dao) {
		this.dao = dao;
	}
	
	public Comment createComment(Comment comment) {
		return dao.save(comment);
	}
		
	public Comment LikeComment(Comment comment, User user) {
		Set<User> likers = comment.getLikers(); 
		
		if(likers.contains(user)) {
			comment.getLikers().remove(user);
		} else {
			likers.add(user);
		}
		
		comment.setLikers(likers);
		
		return dao.save(comment);
	}
	
	public Comment getCommentById(int id) {
		if(dao.existsById(id))
			return dao.getById(id);
		else
			return null;
	}
}
