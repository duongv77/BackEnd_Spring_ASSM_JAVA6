package duong.dev.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.ProductypeDTO;
import duong.dev.entity.Productype;
import duong.dev.mapper.ProducTypeMapper;
import duong.dev.repo.ProductypeRepository;

@Component
public class ProductypeLogic {
	@Autowired ProductypeRepository productypeRepo;
	@Autowired ProducTypeMapper productypeMapper;
	
	public List<ProductypeDTO> Show() {
		List<Productype> entity = productypeRepo.findAll();
		List<ProductypeDTO> productypeDTO = new ArrayList<ProductypeDTO>();
		for (int i=0; i<entity.size(); i++) {
			productypeDTO.add(productypeMapper.convertToDTO(entity.get(i)));
		}
		return productypeDTO;
	}
}
