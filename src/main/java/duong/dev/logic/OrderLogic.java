package duong.dev.logic;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.JwtTokenUtil;
import duong.dev.dto.DoanhThuDTO;
import duong.dev.entity.Order;
import duong.dev.entity.User;
import duong.dev.mapper.UserMapper;
import duong.dev.repo.OrderRepository;
import duong.dev.service.ParamService;

@Service
public class OrderLogic {
	
	@Autowired private ParamService paramSV;
	@Autowired private OrderRepository orderRepo;
	@Autowired private JwtTokenUtil jwtTokenUtil;
	@Autowired private UserMapper userMapper;
	
	public int createOrder(String address) throws ServletException,IOException{
		User user = userMapper.convertToEntity(jwtTokenUtil.getUserToToken());
		Order orderE = new Order();
		orderE.setCreateDate(paramSV.time());
		orderE.setAddress(address);
		orderE.setUser(user);
		orderE.setTotal(0);
		orderRepo.save(orderE);
		return orderE.getId();
	}
	
	public DoanhThuDTO doanhThu() {
		DoanhThuDTO dtD = new DoanhThuDTO();
		dtD.setThang1(orderRepo.doanhThuThang(1));
		dtD.setThang2(orderRepo.doanhThuThang(2));
		dtD.setThang3(orderRepo.doanhThuThang(3));
		dtD.setThang4(orderRepo.doanhThuThang(4));
		dtD.setThang5(orderRepo.doanhThuThang(5));
		dtD.setThang6(orderRepo.doanhThuThang(6));
		dtD.setThang7(orderRepo.doanhThuThang(7));
		dtD.setThang8(orderRepo.doanhThuThang(8));
		dtD.setThang9(orderRepo.doanhThuThang(9));
		dtD.setThang10(orderRepo.doanhThuThang(10));
		dtD.setThang11(orderRepo.doanhThuThang(11));
		dtD.setThang12(orderRepo.doanhThuThang(12));
		return dtD;
	}
}
