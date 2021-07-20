package duong.dev.dto;

import javax.validation.constraints.NotNull;

import duong.dev.entity.Productype;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {
	private Integer id;
	
	private String name;
	
	@NotNull
	private Integer sale;
}
