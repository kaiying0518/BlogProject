package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.service.UsersService;

@Controller
public class UsersRegisterController {
	// UsersService をDI（依存性注入）して、ユーザー登録の処理を委譲する
	@Autowired
	private UsersService usersService;
	//ユーザー登録画面の表示
	@GetMapping("/users/register")
	public String getUsersRegisterPage() {
		return "register";
	}
    //ユーザー登録処理
	@PostMapping("/users/register/process")
	public String usersRegisterProcess(@RequestParam String accountName, @RequestParam String accountEmail,
			@RequestParam String password) {
		// ユーザー作成に成功した場合はログイン画面へリダイレクト
		if (usersService.createAccount(accountEmail, accountName, password)) {
			return "redirect:/users/login";

		} else {
			 // 失敗した場合は登録画面に戻る
			return "register";
		}
	}
}
