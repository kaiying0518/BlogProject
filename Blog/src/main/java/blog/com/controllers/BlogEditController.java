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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.model.entity.Blog;
import blog.com.model.entity.Users;
import blog.com.service.BlogService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogEditController {
	// BlogServiceをDIで注入
	@Autowired
	private BlogService blogService;
	 // セッション情報を取得するためにDI
	@Autowired
	private HttpSession session;
    //ブログ編集ページを表示する（GETリクエスト）
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		// セッションからログイン中のユーザー情報を取得
		Users users = (Users) session.getAttribute("loginUsersInfo");
		// ログインしていない場合、ログインページにリダイレクト
		if (users == null) {
			return "redirect:/users/login";
		} else {
			// ブログIDに該当するブログ情報を取得
			Blog blog = blogService.blogEditCheck(blogId);
			 // 該当ブログが存在しない場合、一覧にリダイレクト
			if (blog == null) {
				return "redirect:/blog/list";
			} else {
				 // 編集画面に必要なデータをモデルに追加
				model.addAttribute("accountName", users.getAccountName());
				model.addAttribute("blog", blog);
				return "blog_edit.html";
			}
		}
	}
//ブログ編集処理を行う（POSTリクエスト）
	@PostMapping("/blog/edit/process")
	public String blogUpdate(@RequestParam String blogTitle, @RequestParam String blogContent,
			@RequestParam MultipartFile blogImage, @RequestParam String categoryName, @RequestParam Long blogId) {
		 // セッションからログイン中のユーザー情報を取得
		Users users = (Users) session.getAttribute("loginUsersInfo");
		// 未ログインの場合はログインページにリダイレクト
		if (users == null) {
			return "redirect:/users/login";
		} else {
			 // 画像ファイル名にタイムスタンプを付けてユニークにする
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				 // アップロードされた画像を指定パスに保存
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 // ブログ更新処理を実行
			 // 成功すれば一覧へ、失敗すれば再編集画面へリダイレクト
			if (blogService.blogUpdate(blogId, blogTitle, blogContent, fileName, categoryName, users.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit/" + blogId;
			}
		}
	}

}
