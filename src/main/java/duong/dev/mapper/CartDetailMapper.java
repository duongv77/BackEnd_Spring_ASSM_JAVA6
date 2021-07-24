package duong.dev.mapper;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.CartDetailDTO;
import duong.dev.entity.Cartdetail;

@Component
public class CartDetailMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Cartdetail convertToEntity(CartDetailDTO cartdetailDTO) {
		Cartdetail entity = new Cartdetail();
		mapper.map(cartdetailDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public CartDetailDTO convertToDTO(Cartdetail entity) {
		CartDetailDTO cartdetailDTO = new CartDetailDTO();
		mapper.map(entity, cartdetailDTO);
		return cartdetailDTO;
	}
}
