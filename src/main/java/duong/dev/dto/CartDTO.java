package duong.dev.dto;

import java.util.List;


import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CartDTO {
	
	private Integer id;
	
	private UserDTO user;
	
	private List<CartDetailDTO> cartdetail;
}
