package duong.dev.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.UserDTO;
import duong.dev.entity.User;

@Component
public class UserMapper {
	@Autowired 
	private ModelMapper mapper;
	
	public User convertToEntity(UserDTO userDTO) {
		User entity = new User();
		mapper.map(userDTO, entity);
		return entity;
	}
	public List<User> convertToListEntity(List<UserDTO> listUserDTO){
		List<User> listEntity =new ArrayList<User>();
		mapper.map(listUserDTO, listEntity);
		return listEntity;
	}
	public UserDTO convertToDTO(User entity) {
		UserDTO userDTO = new UserDTO();
		mapper.map(entity, userDTO);
		return userDTO;
	}
	public List<UserDTO> convertToListDTO(List<User> listEntity){
		List<UserDTO> listDTO =new ArrayList<UserDTO>();
		mapper.map(listEntity, listDTO);
		return listDTO;
	}
}
