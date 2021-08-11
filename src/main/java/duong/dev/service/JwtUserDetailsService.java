package duong.dev.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import duong.dev.entity.User;
import duong.dev.repo.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	@Autowired private UserRepository userRepo;
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Acount không tồn tại");
		}
		String password = user.getPassword();
		String[] roles = user.getUserole().stream().map(rn -> rn.getRole().getName())
							.collect(Collectors.toList()).toArray(new String[0]);
		return org.springframework.security.core.userdetails.User.withUsername(username).password(password).roles(roles).build();
	}
}




























