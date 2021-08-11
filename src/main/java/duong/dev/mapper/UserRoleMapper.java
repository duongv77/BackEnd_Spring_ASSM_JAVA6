package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.UserRoleDTO;
import duong.dev.entity.UserRole;

@Component
public class UserRoleMapper {
	@Autowired 
	private ModelMapper mapper;
	
		//convert từ DTO về entity
		public UserRole convertToEntity(UserRoleDTO useroleDTO) {
			UserRole entity = new UserRole();
			mapper.map(useroleDTO, entity);
			return entity;
		}
		
		//convert từ entity về DTO
		public UserRoleDTO convertToDTO(UserRole entity) {
			UserRoleDTO useroleDTO = new UserRoleDTO();
			mapper.map(entity, useroleDTO);
			return useroleDTO;
		}
}
