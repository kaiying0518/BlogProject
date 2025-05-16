package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersLogoutController {
	@Autowired
	private HttpSession session;

	@GetMapping("/users/logout")
	public String usersLogout() {
		session.invalidate();
		return "redirect:/users/login";
	}
}
