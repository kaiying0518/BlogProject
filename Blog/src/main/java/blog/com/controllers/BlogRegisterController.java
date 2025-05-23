package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.model.entity.Users;
import blog.com.service.BlogService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogRegisterController {
	// ブログ処理用のサービスをDI（依存性注入）
	@Autowired
	private BlogService blogService;
	// ログイン情報などを取得するために HttpSession をDI
	@Autowired
	private HttpSession session;
    //ブログ登録画面を表示する
	@GetMapping("/blog/register")
	public String getBlogRegisterPage(Model model) {
		// セッションからログイン中のユーザーを取得
		Users users = (Users) session.getAttribute("loginUsersInfo");
		// 未ログインならログイン画面へリダイレクト
		if (users == null) {
			return "redirect:/users/login";
		} else {
			// ユーザー名を画面に渡す
			model.addAttribute("accountName", users.getAccountName());
			return "blog_register";
		}

	}

	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(@RequestParam String blogTitle, @RequestParam String blogContent,
			@RequestParam MultipartFile blogImage, @RequestParam String categoryName) {
		 // セッションからログインユーザーを取得
		Users users = (Users) session.getAttribute("loginUsersInfo");
		 // 未ログインならログイン画面へ
		if (users == null) {
			return "redirect:/users/login";
		} else {
			// 画像ファイル名をタイムスタンプ付きで生成
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				// アップロードされた画像を保存する
				Files.copy(blogImage.getInputStream(), Path.of("./images/" + fileName));
			} catch (IOException e) {

				e.printStackTrace();
			}
			// ブログをDBに登録
			if (blogService.createBlog(blogTitle, blogContent, fileName, categoryName, users.getAccountId())) {
				return "redirect:/blog/list";

			} else {
				return "blog_register";
			}
		}
	}
}
