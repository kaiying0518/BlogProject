package blog.com.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blog {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long blogId;
	private String blogTitle;
	private String blogContent;
	private String blogImage;
	private String categoryName;
	private Long accountId;
	@Column(nullable = false)
	private int viewCount;
	public Blog() {
	}
	public Blog(String blogTitle, String blogContent, String blogImage, String categoryName, Long accountId) {
		this.blogTitle = blogTitle;
		this.blogContent = blogContent;
		this.blogImage = blogImage;
		this.categoryName = categoryName;
		this.accountId = accountId;
		
		this.viewCount = 0; 
		
	}
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogtId) {
		this.blogId = blogtId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public String getBlogImage() {
		return blogImage;
	}
	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	 public int getViewCount() {
	        return viewCount; 
	    }

	    public void setViewCount(int viewCount) {
	        this.viewCount = viewCount; 
	    }

	    // 增加阅读次数
	    public void incrementViewCount() {
	        this.viewCount++; 
	    }
	
}
