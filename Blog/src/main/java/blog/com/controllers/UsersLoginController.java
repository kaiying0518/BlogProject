package blog.com.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import blog.com.model.entity.Users;
import blog.com.service.UsersService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersLoginController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private HttpSession session;

	@GetMapping("/users/login")
	public String getUsersLoginPage() {
		return "login";
	}

	@PostMapping("/users/login/process")
	public String usersLoginProcess(@RequestParam String accountEmail, @RequestParam String password,Model model) {
		Users users = usersService.loginCheck(accountEmail, password);
		if (users == null) {
			 model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
			return "login";
		} else {
			session.setAttribute("loginUsersInfo", users);
			return "redirect:/blog/list";
		}
	}

}
