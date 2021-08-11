package duong.dev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.UserRole;

@Repository
public interface UserRoleRepositoty extends JpaRepository<UserRole, Integer>{

}
