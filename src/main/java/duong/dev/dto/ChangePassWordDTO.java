package duong.dev.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassWordDTO {
	@NotNull
	private String pwold;
	@NotNull
	private String pwnew;
	@NotNull
	@Email
	private String email;
}
