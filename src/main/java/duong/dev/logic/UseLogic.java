package duong.dev.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import duong.dev.JwtTokenUtil;
import duong.dev.dto.ChangePassWordDTO;
import duong.dev.dto.JwtRequest;
import duong.dev.dto.JwtResponse;
import duong.dev.dto.LoginDTO;
import duong.dev.dto.ProductDTO;
import duong.dev.dto.UserDTO;
import duong.dev.entity.Cart;
import duong.dev.entity.User;
import duong.dev.libs.HashUtil;
import duong.dev.mapper.UserMapper;
import duong.dev.repo.CartRepository;
import duong.dev.repo.UserRepository;
import duong.dev.service.CookieService;
import duong.dev.service.ParamService;
import duong.dev.service.SendMail;
import duong.dev.service.SessionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import duong.dev.service.JwtUserDetailsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UseLogic {
	@Autowired private UserRepository userRepo;
	
	@Autowired private HttpSession session;
	
	@Autowired 
	SendMail sendEmail;
	
	@Autowired private  ParamService service;
	@Autowired private UserMapper usermaper;
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtUserDetailsService userDetailsService;
	@Autowired private JwtTokenUtil jwtTokenUtil;
	@Autowired private CartRepository cartRepo;
	
	
	

	private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
	private static final String alphaUpperCase = alpha.toUpperCase();
	private static final String digits = "0123456789";
	private static final String specials = "%@#$";
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static final String ALL = alpha + alphaUpperCase + digits + specials;
	private static Random generator = new Random();
	
	private String pwRoanDomPrivate = null;

	public String randomPassword() {
		List<String> result = new ArrayList<>();
		Consumer<String> appendChar = s -> {
			int number = randomNumber(0, s.length() - 1);
			result.add("" + s.charAt(number));
		};
		appendChar.accept(digits);
		appendChar.accept(specials);
		while (result.size() < 8) {
			appendChar.accept(ALL);
		}
		Collections.shuffle(result, generator);
		return String.join("", result);
	}

	public static int randomNumber(int min, int max) {
		return generator.nextInt((max - min) + 1) + min;
	}
	
	public boolean checkMail(String email) {
		try {
			User entity = userRepo.finByEmail(email);
			System.out.println(entity);
			if(entity==null) return false;
		} catch (Exception e) {
			System.out.println("Lỗi check email tồn tại hay không!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean ụpdatePassword(String email) {
		System.out.println(email);
		try {
			User entity = userRepo.finByEmail(email);
			pwRoanDomPrivate = randomPassword();
			if(entity!=null) {
				entity.setPassword(pwRoanDomPrivate);
				entity.setPassword(HashUtil.hash(pwRoanDomPrivate));
				userRepo.save(entity);
			}else {
				System.out.println("Không tìm thấy acount!!");
				pwRoanDomPrivate=null;
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update mật khẩu lỗi rồi!");
			pwRoanDomPrivate=null;
			return false;
		}
		return true;
	}
	
	public LoginDTO login() {
		LoginDTO loginDTO = new LoginDTO();
		
		return loginDTO;
	}
	
	public LoginDTO loginJwt(String username, String password) throws Exception {
		authenticate(username, password);
		final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
		
		User entity = userRepo.findByUsername(username);
		LoginDTO userLogin = new LoginDTO();
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		userLogin.setAccesstoken(token);
		userLogin.setEmail(entity.getEmail());
		userLogin.setId(entity.getId());
		userLogin.setPhoto(entity.getPhoto());
		userLogin.setActivated(entity.getActivated());
		userLogin.setFullname(entity.getFullname());
		userLogin.setAdmin(entity.getAdmin());
        
        return userLogin;
	}
	
	 private void authenticate(String username, String password) throws Exception {
	        try {
	        	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	        } catch (DisabledException e) {
	            throw new Exception("USER_DISABLED", e);
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }
	    }
	
//	public UserDTO login(UserDTO userDTO) {
//		User userEntity = userRepo.finByEmail(userDTO.getEmail());
//		System.out.println("Mk gửi lên: " + userDTO.getPassword());
//		boolean checkPwd = false;
//		if(userEntity != null) {
//			checkPwd = HashUtil.verifile(userDTO.getPassword(), userEntity.getPassword());
//			System.out.println("Check đúng pass chưa : "+checkPwd);
//		}
//		
//		if(userEntity == null || !checkPwd) {
//			return null;
//		}
//		session.setAttribute("userLogin", userEntity);
//		System.out.println("sau login "+ session.getAttribute("userLogin"));
//		return usermaper.convertToDTO(userEntity);
//	}
	
	public void logout() {

		System.out.println("trước logout "+ session.getAttribute("userLogin"));
		
		session.removeAttribute("userLogin");
		
		System.out.println("sau logout "+ session.getAttribute("userLogin"));
	}
	
	public void senMailPwNew(String email) {
		System.out.println("pw trước lúc send Mail: " + pwRoanDomPrivate);
		String noiDung = "Mật khẩu mới của bạn là: " + pwRoanDomPrivate +" .Lưu ý: mật khẩu có phân biệt chữ hoa chữ thường";
		sendEmail.SenMail(email, noiDung);
		pwRoanDomPrivate=null;
		System.out.println("pw sau khi remove: " + pwRoanDomPrivate);
	}
	
	public boolean checkUsername(String username) {
		User user = userRepo.findByUsername(username);
		if(user == null) return false;
		return true;
	}
	public boolean checkEmail(String email) {
		User entity = userRepo.finByEmail(email);
		if (entity == null) return false;
		return true;
	}
	public int addUser(UserDTO userDTO) {
//		return 1 : Email đã được đăng kí
//		return 2 : Lỗi thêm vào DB		
		//3: username đã tồn tại
		if(checkMail(userDTO.getEmail())) return 1;
		if(checkUsername(userDTO.getUsername())) return 3;
		userDTO.setAdmin(0);
		userDTO.setActivated(1);
		User entity = usermaper.convertToEntity(userDTO);
		entity.setPassword(HashUtil.hash(userDTO.getPassword()));
		try {
			userRepo.save(entity);
			Cart cartEntity = new Cart();
			cartEntity.setUser(entity);
			cartRepo.save(cartEntity);
		} catch (Exception e) {
			System.out.println("Lỗi không  thêm được acount!");
			e.printStackTrace();
			return 2;
		}
		return 0;
	}
	
	public boolean updateUser(UserDTO userDTO) {
//		public String getUsernameFromToken(String token) {
//	        return jwtTokenUtil.getClaimFromToken(token, Claims::getSubject);
//	    }
		if(!service.emailValidate(userDTO.getEmail())) return false;
		User entity = userRepo.finById(userDTO.getId());
		entity.setFullname(userDTO.getFullname());
		entity.setEmail(userDTO.getEmail());
		entity.setPhoto(userDTO.getPhoto());
		
		System.out.println();
		try {
			userRepo.save(entity);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public int changePassWord(ChangePassWordDTO changePw) {
		//Nhận vào email đang đăng nhập ở clien
		//return 1: user không tồn tại
		//return 2: pw sai
		//return 3: lưu pw mới bị lỗi
		//4: pw cũ trùng pw mới
		boolean checkPwd = false;
		
		User entity = userRepo.finById(changePw.getId());
		if(changePw.getPwnew().equalsIgnoreCase(changePw.getPwold())) return 4;
		if(entity==null) {
			return 1;
		}else {
			checkPwd = HashUtil.verifile(changePw.getPwold(), entity.getPassword());
			if(!checkPwd) return 2;
		}
		
		entity.setPassword(HashUtil.hash(changePw.getPwnew()));
		try {
			userRepo.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lưu mật khẩu không thành công !!!");
			return 3;
		}
		return 0;
	}
	
	public UserDTO convertTokenToUser()  throws ServletException, IOException  {
		return jwtTokenUtil.getUserToToken();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
