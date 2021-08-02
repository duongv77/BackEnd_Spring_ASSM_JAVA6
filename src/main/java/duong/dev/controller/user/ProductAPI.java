package duong.dev.controller.user;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.dto.ProductDTO;
import duong.dev.entity.Product;
import duong.dev.logic.ProductLogic;
import duong.dev.mapper.ProductMapper;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductAPI {
	@Autowired ProductLogic productLogic;
	@Autowired ProductMapper proMapper;
	
	@GetMapping("/v1/product")
	private List<ProductDTO> showAll() {
		return productLogic.showLimit();
	}
	
	@GetMapping("/v1/product/hangsx/{id}")
	public List<ProductDTO> showProductToHangsx(@PathVariable("id") Integer id)throws IOException{
		return productLogic.showProductToHangsx(id);
	}
	
	@GetMapping("/v1/product/limit_{limit}/page_{page}/sort_{sort}")
	public List<ProductDTO> showLimit(@PathVariable("limit") Integer limit, @PathVariable("page") Integer page, @PathVariable("sort") Integer sort )throws IOException{
		if(page==null) {
			page=0;
		}
		
		return productLogic.showLimit(limit,page, sort);
	}
	
	@GetMapping("/v1/product/{id}")
	public ProductDTO showProduct(@PathVariable("id") Product entity)throws IOException{
		return proMapper.convertToDTO(entity);
	}
	
	@GetMapping("/v1/product/seach:{key}")
	public List<ProductDTO> seachProduct( @PathVariable("key") String key )throws IOException{
		return productLogic.seachProduct(key);
	}
	
}
