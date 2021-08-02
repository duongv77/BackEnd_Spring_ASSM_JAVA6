package duong.dev.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.dto.PromotiondetailDTO;
import duong.dev.entity.Product;
import duong.dev.entity.Promotion;
import duong.dev.entity.Promotiondetail;
import duong.dev.logic.PromotionDetailLogic;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class PromotionDetailApi {
	@Autowired private PromotionDetailLogic promdLogic;
	
	@GetMapping("/v2/admin/promotion_{idPromotion}/product_{idProduct}")
	public void createPromotionDetail(@PathVariable("idPromotion") Promotion promotion, @PathVariable("idProduct") Product product)throws IOException{
		promdLogic.create(product, promotion);
	}
	
	@DeleteMapping("/v2/admin/proomotiondetail_{id}")
	public ResponseEntity<PromotiondetailDTO> delete(@PathVariable("id") Promotiondetail promdE)throws IOException {
		return ResponseEntity.ok(promdLogic.delete(promdE));
	}  
}
