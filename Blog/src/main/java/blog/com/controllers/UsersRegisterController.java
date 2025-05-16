package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.service.UsersService;

@Controller
public class UsersRegisterController {
	@Autowired
	private UsersService usersService;
	@GetMapping("/users/register")
	public String getUsersRegisterPage() {
		return "register";
	}
	@PostMapping("/users/register/process")
	public String usersRegisterProcess(@RequestParam String accountName,@RequestParam String accountEmail,@RequestParam String password) {
		if(usersService.createAccount(accountEmail, accountName, password)) {
			return "redirect:/users/login";
			
		}else {
			return "register";
		}
	}
}
