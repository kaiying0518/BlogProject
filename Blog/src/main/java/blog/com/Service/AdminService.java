package blog.com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.model.dao.UsersDao;
import blog.com.model.entity.Admin;

@Service
public class AdminService {
	@Autowired
	private UsersDao adminDao;

	public boolean createAdmin(String adminEmail, String adminName, String password) {
		if (adminDao.findByAdminEmail(adminEmail) == null) {
			adminDao.save(new Admin(adminEmail, adminName, password));
			return true;
		} else {
			return false;
		}
	}

	public Admin loginCheck(String adminEmail,String password) {
		Admin admin=adminDao.findByAdminEmailAndPassword(adminEmail, password);
		if(admin==null) {
			return null;
		}else {
			return admin;
		}
	}
	}
