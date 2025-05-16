package blog.com.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.com.model.entity.Blog;

public interface BlogDao extends JpaRepository<Blog, Long> {
	Blog save(Blog blog);
	List<Blog>findAll();
	Blog findByBlogTitle(String blogTitle);
	Blog findByBlogId(Long blogId);
	void deleteByBlogId(Long blogId);
}
