package duong.dev.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.dto.DoanhThuDTO;
import duong.dev.logic.OrderLogic;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminOrderApi {
	@Autowired private OrderLogic orderLogic;
	
	@GetMapping("/v2/admin/doanhthu")
	public ResponseEntity<DoanhThuDTO> doanhThu() {
		return ResponseEntity.ok(orderLogic.doanhThu());
	}
}
