package duong.dev.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.PromotiondetailDTO;
import duong.dev.entity.Product;
import duong.dev.entity.Promotion;
import duong.dev.entity.Promotiondetail;
import duong.dev.mapper.PromotionDetailMapper;
import duong.dev.repo.PromotionDetaiRepository;

@Service
public class PromotionDetailLogic {
	@Autowired private PromotionDetaiRepository promDRepo;
	@Autowired private PromotionDetailMapper promdMapper;
	
	public void create(Product product, Promotion promotion) {
		Promotiondetail promd = new Promotiondetail();
		promd.setProduct(product);
		promd.setPromotion(promotion);
		promDRepo.save(promd);
	}
	
	public PromotiondetailDTO delete(Promotiondetail promdE) {
		promDRepo.delete(promdE);
		return promdMapper.convertToDTO(promdE);
	}
}
