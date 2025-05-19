package blog.com.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.model.dao.BlogDao;
import blog.com.model.entity.Blog;






@Service
public class BlogService {
	@Autowired
	  private BlogDao blogDao;
	 public List<Blog>selectAllBlogList(Long accountId){
		 if(accountId==null) {
		 	return null;
		 }else {
		 	return blogDao.findAll();
		 }
		 
}
	 public boolean createBlog(String blogTitle,String blogContent,String blogImage,String categoryName,Long accountId)	{
		 if(blogDao.findByBlogTitle(blogTitle)==null) {  
			  blogDao.save(new Blog(blogTitle,blogContent,blogImage,categoryName,accountId));
			     return true;
			  }else {
				  return false;
			  }
	 }
	 public Blog blogEditCheck(Long blogId) {
	  if(blogId==null) {
	 return null;
	  }else {
		  return blogDao.findByBlogId(blogId);
	  }
	 }
	  public boolean blogUpdate(Long blogId,String blogTitle,String blogContent,String blogImage,String categoryName,Long accountId) {
		  if(blogId==null) {
			  return false;	  
		  }else {
			  Blog blog=blogDao.findByBlogId(blogId);
			  blog.setBlogTitle(blogTitle);
			  blog.setBlogContent(blogContent);
			  if (blogImage != null && !blogImage.isBlank()) {
			        blog.setBlogImage(blogImage);
			    }
			  blog.setCategoryName(categoryName);
			  blog.setAccountId(accountId);
			  blogDao.save(blog);
			  return true;
		  }

	  
	  }
	 }