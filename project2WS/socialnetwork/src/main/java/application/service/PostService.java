package application.service;

import javax.servlet.http.HttpSession;

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
	 * This method is called with a session object, a string to display, and another
	 * string with the URL of an image to be posted if the user chose to add one. If
	 * the URL has no URI, then the value is set to null, as the user did not upload
	 * a file. A new Post object is created and saved to the Database.
	 * 
	 * @Author Dillon Meier
	 * @param
	 * 
	 */
	public void createPost(HttpSession session, String description, String imgUrl) {
		
		session.setAttribute("loggedinUser", new User(1, "dillon", "meier", "abc", "abc.123@youNme.com", "123"));
		
		if(imgUrl.contentEquals("https://buckylebucket.s3.us-east-2.amazonaws.com/PostPics/"+ session.getAttribute("Session Id").toString() +"/")) {
			String blankUrl = null;
			imgUrl = blankUrl;
		}
		User currentUser = (User)session.getAttribute("loggedinUser");
		Post post = new Post(description, imgUrl, currentUser);
		
		dao.save(post);
	}
}
