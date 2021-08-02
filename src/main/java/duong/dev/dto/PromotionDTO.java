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
public class PromotionDTO {
	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Integer sale;
	
	private String createdate;
	
	private String description;

}
