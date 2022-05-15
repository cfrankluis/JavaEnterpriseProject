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
	
	
	/**
	 * Takes a comment object and adds it to the 
	 * database.
	 * @author Frank Carag
	 * @param comment
	 * @return
	 * The full details of the newly added comment
	 */
	public Comment createComment(Comment comment) {
		return dao.save(comment);
	}
		
	/**
	 * Adds the given user to the given comment's list of likers.
	 * If the user already exists in the list, the user is removed.
	 * @author Frank Carag
	 * @param comment
	 * @param user
	 * @return
	 * The full detail of the liked comment
	 */
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
	
	
	/**
	 * Returns a comment with the given id.
	 * @author Frank Carag
	 * @param id
	 * @return
	 * The full detail of the comment with the
	 * given id.
	 */
	public Comment getCommentById(int id) {
		if(dao.existsById(id))
			return dao.getById(id);
		else
			return null;
	}
}
