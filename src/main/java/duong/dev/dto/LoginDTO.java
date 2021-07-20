package duong.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	private Integer id;
	
	private String email;
	
	private String photo;
	
	private String accesstoken;
	
	private String username;
	
	private Integer activated;
	
	private String fullname;
}
