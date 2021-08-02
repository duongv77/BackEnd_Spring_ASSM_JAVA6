package duong.dev.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.dto.OrderdetailDTO;
import duong.dev.logic.OrderDetailLogic;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderDetailAPI {
	@Autowired private OrderDetailLogic orderDetailLogic;
	
	@PostMapping("/v2/user/orderdetail/{id}")
	private void createOrderDetail(@RequestBody List<OrderdetailDTO> listOrderDetail, @PathVariable Integer id)throws ServletException , IOException {
		if(id<0) return;
		orderDetailLogic.createOrderDetail(listOrderDetail, id);
		
	}
}
