package duong.dev.api;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.json.JsonMapper;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class TestPhotoAPi {
	
//	@PostMapping("/photo")
//	public void name(@RequestParam MultipartFile photo) {
//		System.out.println(photo.getOriginalFilename());
//		System.out.println(photo.getSize());
//		System.out.println(photo.getContentType());
//		System.out.println(photo.getName());
//	}
}
