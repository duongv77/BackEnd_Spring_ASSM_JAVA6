package duong.dev.dto;

import javax.validation.constraints.NotNull;

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
public class CartDetailDTO {
	
	private Integer id;
	
	private String createdate;
	
	@NotNull
	private ProductDTO product;
	
	@NotNull
	private Integer quantity;
	
	
}
