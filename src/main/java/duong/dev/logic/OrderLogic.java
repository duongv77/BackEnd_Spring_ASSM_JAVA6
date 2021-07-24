package duong.dev.logic;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.JwtTokenUtil;
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
		orderRepo.save(orderE);
		return orderE.getId();
	}
}
