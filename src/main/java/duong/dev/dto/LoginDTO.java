package duong.dev.dto;

import java.util.List;

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
	
	private Integer admin;
	
	private String fullname;
	
	private List<UserRoleDTO> userole;
}
