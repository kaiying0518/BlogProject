package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.Service.AdminService;
import blog.com.model.entity.Admin;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLoginController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private HttpSession session;

	@GetMapping("/admin/login")
	public String getAdminLoginPage() {
		return "login";
	}

	@PostMapping("/admin/login/process")
	public String adminLoginProcess(@RequestParam String adminEmail, @RequestParam String password,Model model) {
		Admin admin = adminService.loginCheck(adminEmail, password);
		if (admin == null) {
			 model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
			return "login";
		} else {
			session.setAttribute("loginAdminInfo", admin);
			return "redirect:/blog/list";
		}
	}

}
