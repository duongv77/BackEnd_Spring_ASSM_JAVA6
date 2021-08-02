package duong.dev.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.dto.PromotionDTO;
import duong.dev.entity.Promotion;
import duong.dev.logic.PromotionLogic;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class PromotionApi {
	
	@Autowired private PromotionLogic promotionLogic;
	
	@GetMapping("/v2/admin/promotion")
	public ResponseEntity<List<PromotionDTO>> showAll()throws IOException{
		return ResponseEntity.ok(promotionLogic.showAll());
	}
	
	@GetMapping("/v2/admin/promotion/{id}")
	public ResponseEntity<PromotionDTO> show(@PathVariable("id") Integer id)throws IOException{
		return ResponseEntity.ok(promotionLogic.show(id));
	}
	
	@PostMapping("/v2/admin/promotion")
	public ResponseEntity<PromotionDTO> create(@RequestBody PromotionDTO promD)throws IOException {
		return ResponseEntity.ok(promotionLogic.create(promD));
	}
	
	@DeleteMapping("/v2/admin/promotion/{id}")
	public ResponseEntity<PromotionDTO> delete(@PathVariable("id") Promotion promE)throws IOException {
		return ResponseEntity.ok(promotionLogic.delete(promE));
	} 
	
}
