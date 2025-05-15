package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.service.UsersService;



@Controller
public class AdminRegisterController {
	@Autowired
	private UsersService adminService;
	@GetMapping("/admin/register")
	public String getAdminRegisterPage() {
		return "register";
	}
	@PostMapping("/admin/register/process")
	public String adminRegisterProcess(@RequestParam String adminName,@RequestParam String adminEmail,@RequestParam String password) {
		if(adminService.createAdmin(adminEmail, adminName, password)) {
			return "redirect:/admin/login";
			
		}else {
			return "register";
		}
	}
}
