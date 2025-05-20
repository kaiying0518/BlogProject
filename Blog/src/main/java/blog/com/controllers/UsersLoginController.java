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
	  // ユーザー関連のサービスクラスをDI（依存性注入）
	@Autowired
	private UsersService usersService;
	 // ログイン情報の保持などに使用するセッションオブジェクトをDI
	@Autowired
	private HttpSession session;
    //ログイン画面の表示処理
	@GetMapping("/users/login")
	public String getUsersLoginPage() {
		return "login";
	}
     //ログイン処理（POST）
	@PostMapping("/users/login/process")
	public String usersLoginProcess(@RequestParam String accountEmail, @RequestParam String password, Model model) {
		// サービスを通してメールアドレスとパスワードで認証チェック
		Users users = usersService.loginCheck(accountEmail, password);
		if (users == null) {
			// 認証失敗 → エラーメッセージを表示してログイン画面へ戻る
			model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
			return "login";
		} else {
			// 認証成功 → セッションにログイン情報を保存し、ブログ一覧へリダイレクト
			session.setAttribute("loginUsersInfo", users);
			return "redirect:/blog/list";
		}
	}

}
