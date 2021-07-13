package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.OrderdetailDTO;
import duong.dev.entity.Orderdetail;


@Component
public class OrderDetailMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Orderdetail convertToEntity(OrderdetailDTO orderdetailDTO) {
		Orderdetail entity = new Orderdetail();
		mapper.map(orderdetailDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public OrderdetailDTO convertToDTO(Orderdetail entity) {
		OrderdetailDTO orderdetailDTO = new OrderdetailDTO();
		mapper.map(entity, orderdetailDTO);
		return orderdetailDTO;
	}
}
