package duong.dev.logic;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.entity.Role;
import duong.dev.entity.User;
import duong.dev.entity.UserRole;
import duong.dev.repo.UserRoleRepositoty;

@Service
public class UserRoleLogic {
	@Autowired private UserRoleRepositoty userRoleRepo;
	
	public void createUserRole(User user ,Role role ) throws IOException{
		UserRole userRoleE = new UserRole();
		userRoleE.setRole(role);
		userRoleE.setUser(user);
		userRoleRepo.save(userRoleE); 
	}
	
}
