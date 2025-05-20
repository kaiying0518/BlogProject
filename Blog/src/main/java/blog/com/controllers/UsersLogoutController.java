package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersLogoutController {
	// セッションを扱うための HttpSession をDI（依存性注入）
	@Autowired
	private HttpSession session;
    //ログアウト処理（GET）
	@GetMapping("/users/logout")
	public String usersLogout() {
		// セッションの無効化（ログアウト状態にする）
		session.invalidate();
		return "redirect:/users/login";
	}
}
