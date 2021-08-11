package duong.dev.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.dto.ProductypeDTO;
import duong.dev.logic.ProductypeLogic;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminProductTypeApi {
	
	@Autowired ProductypeLogic productypeLogic;
	
	@GetMapping("/v2/admin/hangsx")
	public ResponseEntity<List<ProductypeDTO>> getAll() {
		return ResponseEntity.ok(productypeLogic.Show());
	}
}
