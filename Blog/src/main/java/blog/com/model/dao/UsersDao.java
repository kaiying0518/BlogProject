package blog.com.model.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import blog.com.model.entity.Users;

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {
	Users save(Users users);

	Users findByAccountEmail(String accountEmail);

	Users findByAccountEmailAndPassword(String accountEmail, String password);
}
