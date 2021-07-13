package duong.dev.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;

import duong.dev.dto.ChangePassWordDTO;
import duong.dev.dto.UserDTO;
import duong.dev.entity.User;
import duong.dev.libs.HashUtil;
import duong.dev.mapper.UserMapper;
import duong.dev.repo.UserRepository;
import duong.dev.service.CookieService;
import duong.dev.service.ParamService;
import duong.dev.service.SendMail;
import duong.dev.service.SessionService;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class UseLogic {
	@Autowired private UserRepository userRepo;
	
	@Autowired private HttpSession session;
	
	@Autowired 
	SendMail sendEmail;
	
	@Autowired private CookieService cookie;
	@Autowired private  ParamService service;
	@Autowired private UserMapper usermaper;
	

	public boolean checkEmail(String email) {
		User entity = userRepo.finByEmail(email);
		System.out.println(entity);
		if (entity == null) return false;
		return true;
	}

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
	
	public UserDTO login(UserDTO userDTO) {
		User userEntity = userRepo.finByEmail(userDTO.getEmail());
		System.out.println("Mk gửi lên: " + userDTO.getPassword());
		boolean checkPwd = false;
		if(userEntity != null) {
			checkPwd = HashUtil.verifile(userDTO.getPassword(), userEntity.getPassword());
			System.out.println("Check đúng pass chưa : "+checkPwd);
		}
		
		if(userEntity == null || !checkPwd) {
			return null;
		}
		session.setAttribute("userLogin", userEntity);
		System.out.println("sau login "+ session.getAttribute("userLogin"));
		return usermaper.convertToDTO(userEntity);
	}
	
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
	
	public int addUser(UserDTO userDTO) {
//		return 1 : Email đã được đăng kí
//		return 2 : Lỗi thêm vào DB		
		if(checkMail(userDTO.getEmail())) return 1;
		userDTO.setAdmin(0);
		userDTO.setActivated(1);
		User entity = usermaper.convertToEntity(userDTO);
		entity.setPassword(HashUtil.hash(userDTO.getPassword()));
		try {
			userRepo.save(entity);
		} catch (Exception e) {
			System.out.println("Lỗi không  thêm được acount!");
			e.printStackTrace();
			return 2;
		}
		return 0;
	}
	
	public boolean updateUser(UserDTO userDTO) {
		User entity = usermaper.convertToEntity(userDTO);
		
		try {
			entity.setPassword(userRepo.finById(entity.getId()).getPassword());
			userRepo.save(entity);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public int changePassWord(ChangePassWordDTO changePw) {
		//Nhận vào email đang đăng nhập ở clien
		//return 1: pw giống nhau
		//return 2: email sai định dạng hoặc k chính xác
		//return 3: lưu pw mới bị lỗi
		//return 4: pw sai
		if(changePw.getPwold().equals(changePw.getPwnew())) return 1;
		if(!service.emailValidate(changePw.getEmail())) {
			
			return 2;
		}
		User userEntity = userRepo.finByEmail(changePw.getEmail());
		boolean checkPwd = false;
		if(userEntity!=null) {
			checkPwd = HashUtil.verifile(changePw.getPwold(), userEntity.getPassword());
			if(!checkPwd) return 4;
		}
		if(userEntity == null)return 2;
		userEntity.setPassword(HashUtil.hash(changePw.getPwnew()));
		try {
			userRepo.save(userEntity);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lưu mật khẩu sai !!!");
			return 3;
		}
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
}
