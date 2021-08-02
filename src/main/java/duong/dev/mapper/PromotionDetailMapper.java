package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.PromotiondetailDTO;
import duong.dev.entity.Promotiondetail;

@Component
public class PromotionDetailMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Promotiondetail convertToEntity(PromotiondetailDTO promotionDTO) {
		Promotiondetail entity = new Promotiondetail();
		mapper.map(promotionDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public PromotiondetailDTO convertToDTO(Promotiondetail entity) {
		PromotiondetailDTO promotionDTO = new PromotiondetailDTO();
		mapper.map(entity, promotionDTO);
		return promotionDTO;
	}
}
