package duong.dev.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.entity.Role;
import duong.dev.repo.RoleRepository;

@Service
public class RoleLogic {
	@Autowired private RoleRepository roleRepo;
	
	public Role getRoleByID(Integer id) {
		return roleRepo.getById(id);
	}
}
