package blog.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.model.dao.BlogDao;
import blog.com.model.entity.Blog;

@Service
public class BlogService {
	 // BlogDao を Spring が自動的にインジェクト（依存性注入）する
	@Autowired
	private BlogDao blogDao;
	
	public void save(Blog blog) {
	    blogDao.save(blog);
	}
    public Blog incrementViewCount(Long blogId) {
        Blog blog = blogDao.findByBlogId(blogId);
        if (blog != null) {
            blog.setViewCount(blog.getViewCount() + 1); 
            blogDao.save(blog); 
        }
        return blog;
    }
	// 指定されたアカウントIDに基づいて、全ブログ記事を取得する
	public List<Blog> selectAllBlogList(Long accountId) {
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}

	}
	// 新しいブログ記事を作成する
	public boolean createBlog(String blogTitle, String blogContent, String blogImage, String categoryName,
			Long accountId) {
		// タイトルが重複していない場合のみ保存
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogTitle, blogContent, blogImage, categoryName, accountId));
			return true;
		} else {
			return false;
		}
	}
	// 編集対象のブログ記事を blogId により取得する
	public Blog blogEditCheck(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}
	// 指定された情報でブログ記事を更新する
	public boolean blogUpdate(Long blogId, String blogTitle, String blogContent, String blogImage, String categoryName,
			Long accountId) {
		if (blogId == null) {
			return false;
		} else {
			// 対象のブログ記事を取得
			Blog blog = blogDao.findByBlogId(blogId);
			// 各項目を上書き
			blog.setBlogTitle(blogTitle);
			blog.setBlogContent(blogContent);
			if (blogImage != null && !blogImage.isBlank()) {
				blog.setBlogImage(blogImage);
			}
			blog.setCategoryName(categoryName);
			blog.setAccountId(accountId);
			// 更新を保存
			blogDao.save(blog);
			return true;
		}

	}
	// 指定された blogId のブログ記事を削除する
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
	public List<Blog> searchBlogByTitle(String keyword) {
	    return blogDao.findByBlogTitleContaining(keyword);
	}

	public List<Blog> searchBlogByCategory(String keyword) {
	    return blogDao.findByCategoryNameContaining(keyword);
	}
}