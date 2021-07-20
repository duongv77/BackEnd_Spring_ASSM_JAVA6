package duong.dev.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import duong.dev.entity.User;
import duong.dev.libs.HashUtil;
import duong.dev.repo.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	@Autowired private UserRepository userRepo;
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		System.out.println(username);
		User user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Acount không tồn tại");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername() , user.getPassword(), new ArrayList<>()) ;
	}
	
}


