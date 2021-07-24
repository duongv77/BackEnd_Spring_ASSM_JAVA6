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
import duong.dev.entity.Cartdetail;
import duong.dev.mapper.CartDetailMapper;
import duong.dev.repo.CartDetailRepository;

@Service
public class CartdetailLogic {
	@Autowired private CartDetailRepository cartdetailRepo;
	@Autowired private JwtTokenUtil jwtTokenUtil;
	@Autowired private CartDetailMapper cartdetailMapper;
	
	public void deleteListCartDetail() throws ServletException, IOException {
		UserDTO user = jwtTokenUtil.getUserToToken();
		List<Cartdetail> listCartdetail = cartdetailRepo.finByIdUser(user.getId());
		for(int i = 0 ; i < listCartdetail.size() ; i++) {
			cartdetailRepo.delete(listCartdetail.get(i));
		}
	}
	
	public List<CartDetailDTO>  deleteChecked(List<CartDetailDTO> listCartdetailDelete) throws ServletException, IOException {
		System.out.println("hehe " + listCartdetailDelete.get(0).getId());
		
		List<Cartdetail> cartdE = new ArrayList<Cartdetail>();
		for(int i = 0; i<listCartdetailDelete.size() ; i++) {
			cartdE.add(cartdetailMapper.convertToEntity(listCartdetailDelete.get(i)));
		}
		for(int i = 0; i<cartdE.size() ; i++) {
			this.cartdetailRepo.deleteByIdCustom(cartdE.get(i).getId());
		}
		UserDTO user = jwtTokenUtil.getUserToToken();
		System.out.println("id user " + user.getId());
		List<Cartdetail> listCartdetail = cartdetailRepo.finByIdUser(user.getId());
		List<CartDetailDTO> cartdetaiD = new ArrayList<CartDetailDTO>();
		for(int i= 0;i < listCartdetail.size(); i++) {
			cartdetaiD.add(cartdetailMapper.convertToDTO(listCartdetail.get(i)));
		}
		return cartdetaiD;
	}
}	
