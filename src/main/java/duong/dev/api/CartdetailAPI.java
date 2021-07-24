package duong.dev.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.dto.CartDetailDTO;
import duong.dev.logic.CartdetailLogic;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class CartdetailAPI {
	@Autowired private CartdetailLogic cartdetailLogic;
	
	@PostMapping("/v2/user/cartdetail")
		public List<CartDetailDTO> deleteChecked(@RequestBody List<CartDetailDTO> listCartdetailDelete) throws ServletException, IOException{
		return cartdetailLogic.deleteChecked(listCartdetailDelete);
	}
}
