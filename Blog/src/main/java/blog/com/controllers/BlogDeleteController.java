package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.model.entity.Users;
import blog.com.service.BlogService;
import blog.com.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {
	 // BlogService を DI（依存性注入）
	@Autowired
	private BlogService blogService;
	 // セッションオブジェクトを DI
	@Autowired
	private HttpSession session;
	// ブログ削除処理のエンドポイント（POSTリクエスト用）
	@PostMapping("/blog/delete")
	// セッションからログインユーザー情報を取得
	public String blogDelete(Long blogId) {
		Users users = (Users) session.getAttribute("loginUsersInfo");
		if (users == null) {
			 // 未ログインの場合、ログインページにリダイレクト
			return "redirect:/users/login";
		} else {
			 // 削除処理の実行
			if (blogService.deleteBlog(blogId)) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit" + blogId;
			}
		}

	}
}
