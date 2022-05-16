package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PageController {

	@GetMapping("")
	public String showHomePage() {
		return "/html/welcome.html";
	}

	@GetMapping("/globalfeedpage")
	public String showBlock() {
		return "/html/globalfeedpage.html";
	}

	@GetMapping("/profilepage")
	public String greeting() {
		return "/html/profilepage.html";
	}
	
//	@GetMapping("/forgot-password")
//	public String forgotPassword() {
//		return "/html/forgot-password.html";
//	}
}
