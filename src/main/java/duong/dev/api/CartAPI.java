package duong.dev.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.dto.CartDetailDTO;
import duong.dev.dto.ProductDTO;
import duong.dev.logic.CartLogic;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class CartAPI {
	
	@Autowired private CartLogic cartLogic;
	
	@GetMapping("/v2/user/card")
	public List<CartDetailDTO> listCard()throws ServletException, IOException{
		return cartLogic.listProductCard();
	}
	
	@PutMapping("/v2/user/card")
	public CartDetailDTO updateQuanTity(@RequestBody CartDetailDTO cartdetailDTO) throws IOException{
		return cartLogic.updateQuantity(cartdetailDTO);
	}
	
	@GetMapping("/v2/user/card/product:{id}")
	public void addProduct(@PathVariable("id") Integer id)throws ServletException, IOException{
		cartLogic.addProduct(id);
	}
	
	@DeleteMapping("/v2/user/card/product:{id}")
	public void deleteCartDetail(@PathVariable("id") Integer id) throws IOException {
		cartLogic.deleteCartdetail(id);
	}
}
