package duong.dev;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import duong.dev.dto.UserDTO;
import duong.dev.entity.User;
import duong.dev.repo.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable{
	private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    
    @Autowired private UserRepository userRepo;
    
    @Autowired HttpServletRequest request;
    
    @Value("${jwt.secret}")
    private String secret;
    
    //lấy tên user ng dùng từ token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    
    // lấy ngày hết hạn từ mã jwt
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
  //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
    	Claims clams = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return clams;
    }
    
  //kiểm tra hạn của token
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    //thực hiện validate token
    public Boolean validateToken(String token, UserDetails userDetails) throws ServletException, IOException {
        final UserDTO user = getUserToToken();
        return (user.getUsername().equals(userDetails.getUsername()) &&  !isTokenExpired(token));
    }
    
    //convert token thành obj User
   
    
    //tạo token
    private String doGenerateToken(
    		Map<String, Object> claims, String user ) {
    	
    	User userEntity = userRepo.findByUsername(user);
    	
    	User userToken =  new User();
    	userToken.setUsername(user);
    	userToken.setId(userEntity.getId());
    	userToken.setAdmin(userEntity.getAdmin());
    	userToken.setActivated(userEntity.getActivated());
    	userToken.setPassword(userEntity.getPassword());
    	
    	Gson gson = new Gson();
    	String userStr = gson.toJson(userToken);
    	
        return Jwts.builder()
        		.setClaims(claims)
        		.setSubject(userStr)
        		
        		.setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        
        return doGenerateToken(claims, userDetails.getUsername());
    }
    
    //convert token  về obj
    public UserDTO getUserToToken() throws ServletException, IOException {
    	final String requestTokenHeader = request.getHeader("Authorization");
    	String jwtToken = requestTokenHeader.substring(7);
    	
    	Claims clams = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
    	
    	String subject = clams.getSubject();
    	Gson gson = new Gson();
    	UserDTO entity = gson.fromJson(subject, UserDTO.class);
    	return entity;
    }

}
