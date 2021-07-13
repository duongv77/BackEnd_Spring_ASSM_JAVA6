package duong.dev.api;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import duong.dev.dto.ChangePassWordDTO;
import duong.dev.dto.UserDTO;
import duong.dev.dto.test;
import duong.dev.entity.User;
import duong.dev.logic.UseLogic;
import duong.dev.mapper.UserMapper;
import duong.dev.repo.UserRepository;
import duong.dev.service.ParamService;
import duong.dev.service.SendMail;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class UserApi {
	@Autowired private UserRepository userRepo;
	@Autowired private UserMapper usermaper;
	@Autowired private SendMail sendMail;
	@Autowired private UseLogic userLogic;
	@Autowired private ParamService service;
	
	@GetMapping("/user")
	public List<UserDTO> show(){
		List<User> listUser = userRepo.findAll();
		List<UserDTO> model = new ArrayList<UserDTO>();
		for( int i = 0 ; i<listUser.size(); i++) {
			model.add(usermaper.convertToDTO(listUser.get(i)));
		}
		return model;
	}
	
	@PostMapping("/login")
	public UserDTO login(@RequestBody UserDTO userDTO) {
		return userLogic.login(userDTO);
	}
	
	@PostMapping("/photo")
	public int postPhoto(@RequestParam MultipartFile photo) {
		//1: không có ảnh, 2 lỗi upfile, 
		
		if(photo==null) return 1;
		try {
			service.uploadFile(photo);
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
		return 0;
	}
	
	@GetMapping("/logout")
	public void logOut() {
		userLogic.logout();
	}
	
	@PostMapping("/sendmail")
	public boolean sendMailWellCome(@RequestBody UserDTO userDTO) {
		try {
			sendMail.senMaiChaoMung(userDTO.getEmail(), userDTO.getPassword());
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	@PostMapping("/forgetpwsenmail")
	public boolean forGetPwSenMail(@RequestBody UserDTO userEmail) {
		userLogic.senMailPwNew(userEmail.getEmail());
		return true;
	}
	
	@PostMapping("/forgetpw")
	public boolean forGetPw(@RequestBody UserDTO userDTO) {
		if(!userLogic.checkEmail(userDTO.getEmail())) return false;
		if(!userLogic.ụpdatePassword(userDTO.getEmail())) {
			return false;
		}
		return true;
	}
	
	@PostMapping("/user")
	public int add(@RequestBody UserDTO userDTO) {
		return userLogic.addUser(userDTO);
	}
	
	@PutMapping("/user")
	public boolean updateUser(@RequestBody UserDTO userDTO) {
		if(userLogic.updateUser(userDTO)) return false;
		return true;
	}
	
	@PostMapping("/changepw")
	public int changPassWord(@RequestBody ChangePassWordDTO changePw) {
		return userLogic.changePassWord(changePw);
	}
}
