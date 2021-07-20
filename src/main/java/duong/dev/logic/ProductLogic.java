package duong.dev.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import duong.dev.dto.ProductDTO;
import duong.dev.entity.Product;
import duong.dev.mapper.ProductMapper;
import duong.dev.repo.ProductRepository;

@Service
public class ProductLogic {
	@Autowired private ProductMapper proMapper;
	@Autowired private ProductRepository proRepo;
	
	public List<ProductDTO> showAll() {
		List<Product> listProduct =proRepo.findAll();
		List<ProductDTO> listProDTO = new ArrayList<ProductDTO>();
		for(int i= 0; i<listProduct.size(); i++) {
			listProDTO.add(proMapper.convertToDTO(listProduct.get(i)));
		}
		System.out.println(listProDTO.size());
		return listProDTO;
	}
	
	public List<ProductDTO> showProductToHangsx(Integer id) {
		List<Product> listProduct =proRepo.finByIdHangsx(id);
		List<ProductDTO> listProDTO = new ArrayList<ProductDTO>();
		for(int i= 0; i<listProduct.size(); i++) {
			listProDTO.add(proMapper.convertToDTO(listProduct.get(i)));
		}
		return listProDTO;
	}
	
	public List<ProductDTO> showLimit(Integer limit,Integer page, Integer sort) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Product> listProduct = new ArrayList<Product>();
		
		if(sort == 0) {
			 listProduct = proRepo.finLimit(pageable );
		}else if(sort == 1) {
			 listProduct = proRepo.finLimitPriceDESC(pageable );
		}else if(sort == 2) {
			 listProduct = proRepo.finLimitPriceASC(pageable );
		}else {
			listProduct = proRepo.finLimitNameASC(pageable );
		}
		
		//List<Product> listProduct = proRepo.finLimit(pageable );
		List<ProductDTO> listProDTO = new ArrayList<ProductDTO>();
		for(int i= 0; i<listProduct.size(); i++) {
			listProDTO.add(proMapper.convertToDTO(listProduct.get(i)));
		}
		return listProDTO;
	}
}
