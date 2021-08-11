package duong.dev.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.JwtTokenUtil;
import duong.dev.dto.CartDetailDTO;
import duong.dev.dto.UserDTO;
import duong.dev.entity.Cart;
import duong.dev.entity.Cartdetail;
import duong.dev.entity.Product;
import duong.dev.entity.User;
import duong.dev.mapper.CartDetailMapper;
import duong.dev.repo.CartDetailRepository;
import duong.dev.repo.CartRepository;
import duong.dev.repo.ProductRepository;
import duong.dev.service.ParamService;


@Service
public class CartLogic {
	
	@Autowired private JwtTokenUtil jwtTokenUtil;
	@Autowired private CartRepository cartRepo;
	@Autowired private CartDetailRepository cartdetailRepo;
	@Autowired private CartDetailMapper cartdetailMapper;
	@Autowired private ParamService paramSv;
	@Autowired private ProductRepository proRepo; 
	
	public void createCart(User user) {
		Cart cartE = new Cart();
		cartE.setUser(user);
		cartRepo.save(cartE);
	}
	
	public List<CartDetailDTO> listProductCard()  throws ServletException, IOException {
		UserDTO userDTO = jwtTokenUtil.getUserToToken();
		
		Cart cartEntity = cartRepo.finByIdUser(userDTO.getId());
		
		List<Cartdetail> cartdetailEntity = cartdetailRepo.finByIdCard(cartEntity.getId());
		List<CartDetailDTO> cartdetailDTO = new ArrayList<CartDetailDTO>();
		
		for(int i = 0; i<cartdetailEntity.size(); i++) {
			cartdetailDTO.add(cartdetailMapper.convertToDTO(cartdetailEntity.get(i)));
		}
		
		return cartdetailDTO;
	}
	
	public CartDetailDTO updateQuantity(CartDetailDTO cartdetailDTO) {
		
		Cartdetail cartDetailEntity = cartdetailRepo.finByIdCarddetail(cartdetailDTO.getId());
		cartDetailEntity.setQuantity(cartdetailDTO.getQuantity());
		this.cartdetailRepo.save(cartDetailEntity);
		return cartdetailDTO;
	}
	
	public void addProduct(Integer id)throws ServletException, IOException {
		Cartdetail cartdetailE = new Cartdetail();
			cartdetailE = cartdetailRepo.finByIdProduct(id);
			UserDTO userD = jwtTokenUtil.getUserToToken();
			Cart cartE = cartRepo.finByIdUser(userD.getId());
			Product productE = proRepo.getById(id);
			System.out.println(" product : "  + productE);
			if(cartdetailE==null) {
				Cartdetail cartdetailEn = new Cartdetail();
				cartdetailEn.setCart(cartE);
				cartdetailEn.setCreatedate(paramSv.time());
				cartdetailEn.setProduct(productE);
				cartdetailEn.setQuantity(1);
				cartdetailRepo.save(cartdetailEn);
				return;
			}
			cartdetailE.setQuantity(cartdetailE.getQuantity()+1);
			cartdetailRepo.save(cartdetailE);
	}
	
	public void deleteCartdetail(Integer id)throws IOException {
		Cartdetail cartdetaiE =cartdetailRepo.finByIdCarddetail(id);
		cartdetailRepo.delete(cartdetaiE);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
