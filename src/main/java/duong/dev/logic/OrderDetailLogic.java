package duong.dev.logic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.OrderdetailDTO;
import duong.dev.entity.Order;
import duong.dev.entity.Orderdetail;
import duong.dev.repo.OrderDetailRepository;
import duong.dev.repo.OrderRepository;

@Service
public class OrderDetailLogic {
	@Autowired private OrderDetailRepository orderDetailRepo;
	@Autowired private OrderRepository orderRepo;
	@Autowired private OrderDetailRepository orderdetailRepo;
	@Autowired  private CartdetailLogic cartdetailLogic;
	public void createOrderDetail(List<OrderdetailDTO> listOrderDetailDTO, Integer id) throws ServletException,IOException {
		Order order = orderRepo.finByIdOrder(id);
		int sale=0;
		int total=0;
		for(int i=0; i<listOrderDetailDTO.size();i++) {
			
			Orderdetail orderDetailE = new Orderdetail();
			orderDetailE.setQuantity(listOrderDetailDTO.get(i).getQuantity());
			orderDetailE.setProduct(listOrderDetailDTO.get(i).getProduct());
			orderDetailE.setOrder(order);
			sale = listOrderDetailDTO.get(i).getProduct().getPromotiondetail() == null ?  
								100 : 100-listOrderDetailDTO .get(i).getProduct()
								.getPromotiondetail().getPromotion().getSale();
			int price = listOrderDetailDTO.get(i).getProduct()
							.getPrice()*orderDetailE.getQuantity()/100*sale;
			orderDetailE.setPrice(price);
			total += orderDetailE.getPrice();
			orderDetailRepo.save(orderDetailE);
		}
		order.setTotal(total);
		orderRepo.save(order);
		cartdetailLogic.deleteListCartDetail();
	}
	
}
