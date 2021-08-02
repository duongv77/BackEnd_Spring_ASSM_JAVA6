package duong.dev.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import duong.dev.dto.ProductDTO;
import duong.dev.entity.Product;
import duong.dev.mapper.ProductMapper;
import duong.dev.repo.ProductRepository;
import duong.dev.service.ParamService;

@Service
public class ProductLogic {
	@Autowired private ProductMapper proMapper;
	@Autowired private ProductRepository proRepo;
	@Autowired private ParamService prSV;
	
	public List<ProductDTO> showLimit() {
		List<Product> listProduct =proRepo.finPrUser();
		List<ProductDTO> listProDTO = new ArrayList<ProductDTO>();
		for(int i= 0; i<listProduct.size(); i++) {
			listProDTO.add(proMapper.convertToDTO(listProduct.get(i)));
		}
		return listProDTO;
	}
	
	public List<ProductDTO> showAll() {
		List<Product> listProduct =proRepo.findAll();
		List<ProductDTO> listProDTO = new ArrayList<ProductDTO>();
		for(int i= 0; i<listProduct.size(); i++) {
			listProDTO.add(proMapper.convertToDTO(listProduct.get(i)));
		}
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
	
	public List<ProductDTO> seachProduct(String key) {
		List<Product> listProductE = proRepo.finByKeyword(key);
		List<ProductDTO> listProductD =  new ArrayList<ProductDTO>();
		for(int i=0; i<listProductE.size(); i++) {
			listProductD.add(proMapper.convertToDTO(listProductE.get(i)));
		}
		return listProductD;
	}
	public ResponseEntity<ProductDTO> updateAvailableProduct(Integer idproduct,Integer available)throws IOException {
		Product productE = proRepo.finById(idproduct);
		if(productE==null) {
			return ResponseEntity.noContent().build();
		}
		productE.setAvailable(available);
		proRepo.save(productE);
		ProductDTO productD = proMapper.convertToDTO(productE);
		return ResponseEntity.ok(productD);
	}
	public void deleteProduct(ProductDTO productD)throws IOException {
		proRepo.delete(proMapper.convertToEntity(productD));
	}
	
	public ProductDTO updateProduct(ProductDTO productD) {
		Product productE = proMapper.convertToEntity(productD);
		proRepo.save(productE);
		productD.setId(productE.getId());
		return productD;
	}
	
	public ProductDTO createProduct(ProductDTO productD) throws IOException {
		productD.setCreatedate(prSV.time());
		Product productE = proMapper.convertToEntity(productD);
		proRepo.save(productE);
		productD.setId(productE.getId());
		return productD;
	}
	
	public List<ProductDTO> showListProductPromotion(Integer id) throws IOException {
		List<Product> proE = proRepo.finByIdPromotion(id);
		List<ProductDTO> productD = new ArrayList<ProductDTO>();
		for(int i = 0 ; i<proE.size() ; i++) {
			productD.add(proMapper.convertToDTO(proE.get(i)));
		}
		return productD;
	}
	public List<ProductDTO> showListProductNotPromotion() throws IOException {
		System.out.println("nooooooooooooooo");
		List<Product> proE = proRepo.finByIdPromotionNot();
		List<ProductDTO> productD = new ArrayList<ProductDTO>();
		for(int i = 0 ; i<proE.size() ; i++) {
			productD.add(proMapper.convertToDTO(proE.get(i)));
		}
		return productD;
	}
}
























