package duong.dev.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.dto.ProductDTO;
import duong.dev.entity.Product;
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
	
	@PutMapping("/v2/admin/product")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productD) throws IOException {
		return ResponseEntity.ok(proLogic.updateProduct(productD));
	}
	
	@PostMapping("/v2/admin/create/product")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productD) throws IOException {
		System.out.println("hehe");
		return ResponseEntity.ok(proLogic.createProduct(productD));
	}
	
	@GetMapping("/v2/admin/product/promotion_{id}")
	public ResponseEntity<List<ProductDTO>> getListProductPromotion(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(proLogic.showListProductPromotion(id));
	}
	@GetMapping("/v2/admin/product/notpromotion")
	public ResponseEntity<List<ProductDTO>> getListProductNoPromotion() throws IOException{
		return ResponseEntity.ok(proLogic.showListProductNotPromotion());
	}
}
