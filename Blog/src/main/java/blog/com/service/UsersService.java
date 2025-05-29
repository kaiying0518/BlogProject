package blog.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.model.dao.UsersDao;
import blog.com.model.entity.Users;

@Service
public class UsersService {
	// UsersDao（リポジトリ）をSpringが自動的にインジェクト
	@Autowired
	private UsersDao usersDao;

	public boolean createAccount(String accountEmail, String accountName, String password) {
		// メールアドレスが未登録である場合に新規アカウント作成
		if (usersDao.findByAccountEmail(accountEmail) == null) {
			usersDao.save(new Users(accountEmail, accountName, password));
			return true;
		} else {
			// 同じメールアドレスが既に存在する場合は登録失敗
			return false;
		}
	}

           //ログインチェック処理
	public Users loginCheck(String accountEmail, String password) {
		// メールアドレスとパスワードが一致するユーザーを検索
		Users users = usersDao.findByAccountEmailAndPassword(accountEmail, password);
		if (users == null) {
			return null;
		} else {
			return users;
		}
	}
}
