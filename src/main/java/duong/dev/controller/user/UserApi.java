package duong.dev.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;

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
import duong.dev.dto.LoginDTO;
import duong.dev.dto.UserDTO;
import duong.dev.logic.UseLogic;
import duong.dev.service.ParamService;
import duong.dev.service.SendMail;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class UserApi {
	@Autowired private SendMail sendMail;
	@Autowired private UseLogic userLogic;
	@Autowired private ParamService service;
	
	@PostMapping("/v1/login")
	public LoginDTO login(@RequestBody UserDTO userDTO) throws Exception {
		return userLogic.loginJwt(userDTO.getUsername(), userDTO.getPassword());
	}
	

	@PostMapping("/v1/user/photo")
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
	
	@GetMapping("/v1/user/logout")
	public void logOut() {
		userLogic.logout();
	}
	
	@PostMapping("/v1/user/sendmail")
	public boolean sendMailWellCome(@RequestBody UserDTO userDTO) {
		try {
			sendMail.senMaiChaoMung(userDTO.getEmail(), userDTO.getPassword());
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	@GetMapping("/test")
	public UserDTO hihe()  throws ServletException, IOException {
		return userLogic.convertTokenToUser();
	}
	
	@PostMapping("/v1/user/forgetpwsenmail")
	public boolean forGetPwSenMail(@RequestBody UserDTO userEmail) {
		userLogic.senMailPwNew(userEmail.getEmail());
		return true;
	}
	
	@PostMapping("/v1/user/forgetpw")
	public boolean forGetPw(@RequestBody UserDTO userDTO) {
		if(!userLogic.checkEmail(userDTO.getEmail())) return false;
		if(!userLogic.ụpdatePassword(userDTO.getEmail())) {
			return false;
		}
		return true;
	}
	
	@PostMapping("/v1/user/store")
	public int add(@RequestBody UserDTO userDTO) {
		return userLogic.addUser(userDTO);
	}
	
	@PutMapping("/v2/user/update")
	public boolean updateUser(@RequestBody UserDTO userDTO) throws Exception {
		return userLogic.updateUser(userDTO);
	}
	
	@PostMapping("/v2/user/changepw")
	public int changPassWord(@RequestBody ChangePassWordDTO changePw) {
		return userLogic.changePassWord(changePw);
	}
	
	
}
























