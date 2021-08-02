package duong.dev.controller.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.JwtTokenUtil;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class JwtApi {
	
	@Autowired private JwtTokenUtil jwtTokenUtil;
	
	
	@GetMapping("/v2/admin/jwt")
	public Boolean checkJwt(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		return jwtTokenUtil.isTokenExpired(jwtToken);
	}
}
