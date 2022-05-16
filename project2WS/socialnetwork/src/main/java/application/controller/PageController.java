package application.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/profilepage")
	public String greeting() {
		return "/profilepage.html";
	}
}


