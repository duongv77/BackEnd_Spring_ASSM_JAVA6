package duong.dev.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PromotiondetailDTO {
	private  Integer id;
	private PromotionDTO promotion;
	//private ProductDTO product;
}
