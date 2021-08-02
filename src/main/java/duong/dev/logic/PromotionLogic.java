package duong.dev.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.PromotionDTO;
import duong.dev.entity.Promotion;
import duong.dev.mapper.PromotionMapper;
import duong.dev.repo.PromotionRepository;
import duong.dev.service.ParamService;

@Service
public class PromotionLogic {
	@Autowired PromotionRepository promotionRepo;
	@Autowired PromotionMapper promotionMapper;
	@Autowired ParamService parSV;
	
	public List<PromotionDTO> showAll() {
		List<Promotion> promE = promotionRepo.findAll();
		List<PromotionDTO> promD = new ArrayList<PromotionDTO>();
		for(int i = 0 ; i < promE.size() ; i++) {
			promD.add(promotionMapper.convertToDTO(promE.get(i)));
		}
		return promD;
	}
	
	public PromotionDTO show(Integer id) {
		Optional<Promotion> optional = promotionRepo.findById(id);
		PromotionDTO promD =  new PromotionDTO();
		if(optional.isPresent()) {
			Promotion promE = optional.get();
			promD = promotionMapper.convertToDTO(promE);
		}
		return promD;
	}
	
	public PromotionDTO create(PromotionDTO promD) {
		Promotion promE = promotionMapper.convertToEntity(promD);
		promE.setCreatedate(parSV.time());
		promotionRepo.save(promE);
		promD=promotionMapper.convertToDTO(promE);
		return promD;
	}
	public PromotionDTO delete(Promotion promE) {
		promotionRepo.delete(promE);
		PromotionDTO promD = promotionMapper.convertToDTO(promE);
		return promD;
	}
}
