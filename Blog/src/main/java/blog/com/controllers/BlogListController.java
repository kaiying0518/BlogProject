package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.model.entity.Blog;
import blog.com.model.entity.Users;
import blog.com.service.BlogService;

import jakarta.servlet.http.HttpSession;

@Controller

public class BlogListController {
	// セッション情報を取得するために HttpSession をDI（依存性注入）
	@Autowired
	private HttpSession session;
	// ブログに関する処理を行う BlogService をDI
	@Autowired
	private BlogService blogService;
    //ブログ一覧ページの表示処理
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		 // セッションからログインユーザー情報を取得
		Users users = (Users) session.getAttribute("loginUsersInfo");
		 // ユーザーがログインしていない場合はログインページへリダイレクト
		if (users == null) {
			return "redirect:/users/login";
		} else {
			 // ログイン中のユーザーのブログ一覧を取得
			List<Blog> blogList = blogService.selectAllBlogList(users.getAccountId());
			// アカウント名を表示用に渡す
			model.addAttribute("accountName", users.getAccountName());
			// ブログ一覧を渡す
			model.addAttribute("blogList", blogList);
			return "blog_list";
		}
	}
	@GetMapping("/blog/search")
	public String searchBlog(@RequestParam String keyword, Model model) {
	    Users users = (Users) session.getAttribute("loginUsersInfo");
	    if (users == null) {
	        return "redirect:/users/login";
	    }
	    List<Blog> searchResults = blogService.searchBlogByTitle(keyword); 
	    model.addAttribute("accountName", users.getAccountName());
	    model.addAttribute("blogList", searchResults);
	    model.addAttribute("keyword", keyword);
	    return "blog_list";
	}

}
