package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.ProductypeDTO;
import duong.dev.entity.Productype;


@Component
public class ProducTypeMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Productype convertToEntity(ProductypeDTO productypeDTO) {
		Productype entity = new Productype();
		mapper.map(productypeDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public ProductypeDTO convertToDTO(Productype entity) {
		ProductypeDTO productypeDTO = new ProductypeDTO();
		mapper.map(entity, productypeDTO);
		return productypeDTO;
	}
}
