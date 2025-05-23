package blog.com.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.model.entity.Blog;
import jakarta.transaction.Transactional;

@Repository
//トランザクション管理を有効にする
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
	// ブログ情報を保存・更新する
	Blog save(Blog blog);

	// 全ブログ記事を取得する
	List<Blog> findAll();

	// ブログタイトルで1件のブログを取得
	Blog findByBlogTitle(String blogTitle);

	// ブログIDで1件のブログを取得
	Blog findByBlogId(Long blogId);

	// ブログIDでブログを削除
	void deleteByBlogId(Long blogId);

	// タイトルで部分一致検索
	List<Blog> findByBlogTitleContaining(String keyword);

	// カテゴリで部分一致検索
	List<Blog> findByCategoryNameContaining(String keyword);
}
