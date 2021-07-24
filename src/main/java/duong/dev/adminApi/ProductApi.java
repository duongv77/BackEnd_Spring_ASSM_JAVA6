package duong.dev.adminApi;

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

import duong.dev.dto.ProductDTO;
import duong.dev.logic.ProductLogic;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductApi {
	
	@Autowired  private ProductLogic proLogic;
	
	@GetMapping("/v2/admin/product")
	public List<ProductDTO> showListProduct() {
		return proLogic.showAll();
	}
	
	@GetMapping("/v2/admin/product:{product}/available:{available}")
	public ResponseEntity<ProductDTO> updateAvailableProduct(@PathVariable("product") Integer idproduct, @PathVariable("available") Integer available) throws IOException{
		return proLogic.updateAvailableProduct(idproduct, available);
	}
	
	@PostMapping("/v2/admin/product")
	public void deleteProduct(@RequestBody ProductDTO productD) throws IOException {
		proLogic.deleteProduct(productD);
	}
}
