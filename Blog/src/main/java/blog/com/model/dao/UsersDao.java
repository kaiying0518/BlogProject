package blog.com.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import blog.com.model.entity.Users;

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {
	// ユーザー情報を保存または更新するメソッド
	Users save(Users users);
	// メールアドレスを条件にユーザーを1件取得
	Users findByAccountEmail(String accountEmail);
	// メールアドレスとパスワードを両方条件にしてユーザーを取得
	Users findByAccountEmailAndPassword(String accountEmail, String password);
}
