package blog.com.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.model.entity.Blog;
import blog.com.model.entity.Users;
import blog.com.service.BlogService;

import jakarta.servlet.http.HttpSession;

@Controller

public class BlogListController {
@Autowired
private HttpSession session;
@Autowired
private BlogService blogService;
@GetMapping("/blog/list")
public String getBlogList(Model model) {
	Users users=(Users) session.getAttribute("loginUsersInfo");
	if(users==null) {
		return"redirect:/users/login";
	}else {
		List<Blog>blogList=blogService.selectAllBlogList(users.getAccountId());
		model.addAttribute("accountName",users.getAccountName());
		model.addAttribute("blogList",blogList);
		return "blog_list";
	}
}

}
