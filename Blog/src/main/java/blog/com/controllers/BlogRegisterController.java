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
	@Autowired
	private BlogService blogService;
	@Autowired
	private HttpSession session;
	@GetMapping("/blog/register")
	public String getBlogRegisterPage(Model model) {
		Users users=(Users) session.getAttribute("loginUsersInfo");
		if(users==null) {
			return "redirect:/users/login";
		}else {
			model.addAttribute("accountName",users.getAccountName());
			return "product_register";
		}

	}
	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(@RequestParam String blogTitle,@RequestParam String blogContent,@RequestParam MultipartFile blogImage,@RequestParam String categoryName) {
		Users users=(Users) session.getAttribute("loginUsersInfo");
		if(users==null) {
			return "redirect:/users/login";
		}else {
			String fileName=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())+blogImage.getOriginalFilename();
		try {
			Files.copy(blogImage.getInputStream(),Path.of("src/main/resources/static/image/"+fileName));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		if(blogService.createBlog(blogContent,categoryName,fileName,blogTitle,users.getAccountId())) {
			return "redirect:/blog/list";
			
		}else {
			return "blog_register";
		}
	}
	}
}
