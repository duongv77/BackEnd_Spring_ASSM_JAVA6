package duong.dev.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.logic.OrderLogic;
import duong.dev.service.ParamService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderAPI {
	
	@Autowired private OrderLogic orderLogic;
	@Autowired private ParamService paramSV;
	
	
	@PostMapping("/v2/user/order")
	public int createOrder(@RequestBody String address)throws ServletException,IOException {
		if(address=="") return -1;
		return orderLogic.createOrder(address);
	}
	
	
}
