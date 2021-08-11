package duong.dev.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.dto.UserDTO;
import duong.dev.entity.User;
import duong.dev.logic.UseLogic;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminUserApi {
	@Autowired private UseLogic userLogic;
	
	@GetMapping("/v2/admin/user")
	public ResponseEntity<List<UserDTO>> showAll(){
		return ResponseEntity.ok(userLogic.showAll());
	}
	
	@PostMapping("/v2/admin/listuser")
	public ResponseEntity<String> createListUser(@RequestBody List<UserDTO> listUserD) throws IOException{
		return ResponseEntity.ok(userLogic.createListUser(listUserD));
	}
	
	@GetMapping("/v2/admin/user_{id}/activated_{value}")
	public ResponseEntity<UserDTO> updateActivated(@PathVariable("id") User user, @PathVariable("value") Integer value){
		return ResponseEntity.ok(userLogic.updateActivated(user, value));
	}
}
