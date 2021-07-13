package duong.dev.dto;

import java.io.File;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class test {
//	//@NotNull
//		private Integer id;
//		
//		@NotNull
//		@NotBlank
//		private String username;
//		
//		@NotNull
//		@NotBlank
//		@Email
//		private String email;
		
		//@NotNull
		private MultipartFile photo;
		
//		@NotNull
//		private Integer admin;
//		
//		@NotNull
//		private Integer activated;
//		
//		@NotNull(message = "thiếu pw")
//		private String password;
}
