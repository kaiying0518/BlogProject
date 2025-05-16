package blog.com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.model.dao.UsersDao;
import blog.com.model.entity.Users;


@Service
public class UsersService {
	@Autowired
	private UsersDao usersDao;

	public boolean createAccount(String accountEmail, String accountName, String password) {
		if (usersDao.findByAccountEmail(accountEmail) == null) {
			usersDao.save(new Users(accountEmail, accountName, password));
			return true;
		} else {
			return false;
		}
	}

	public Users loginCheck(String accountEmail,String password) {
		Users users=usersDao.findByAccountEmailAndPassword(accountEmail, password);
		if(users==null) {
			return null;
		}else {
			return users;
		}
	}
	}
