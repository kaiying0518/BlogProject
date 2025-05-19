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
	@Autowired
	private BlogService blogService;
	@Autowired
	private HttpSession session;
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		Users users = (Users) session.getAttribute("loginUsersInfo");
		if (users == null) {
			return "redirect:/users/login";
		}else {
			Blog blog = blogService.blogEditCheck(blogId);
			if (blog == null) {
				return "redirect:/blog/list";
			} else {
				model.addAttribute("accountName", users.getAccountName());
				model.addAttribute("blog", blog);
				return "blog_edit.html";
			}
		}
	}
	@PostMapping("/blog/edit/process")
	public String blogUpdate(@RequestParam String blogTitle, @RequestParam String  blogContent,
			@RequestParam MultipartFile blogImage, @RequestParam String categoryName,
			@RequestParam Long blogId) {
		Users users = (Users) session.getAttribute("loginUsersInfo");
		if (users == null) {
			return "redirect:/users/login";
		} else {String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
				+ blogImage.getOriginalFilename();
		try {
			Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/"+fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(blogService.blogUpdate(blogId, blogTitle, blogContent, fileName, categoryName, users.getAccountId())) {
			   return "redirect:/blog/list";
			}else {
				return "redirect:/blog/edit/"+blogId;
			}
		}
	}
	
	
}


