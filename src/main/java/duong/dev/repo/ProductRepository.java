package duong.dev.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("SELECT entity FROM Product entity WHERE productype.id = :id")
	List<Product> finByIdHangsx(@Param("id") Integer id);
	
	@Query("SELECT entity FROM Product entity WHERE id = :id")
	Product finById(@Param("id") Integer id);
	
	@Query("SELECT entity FROM Product entity WHERE available=1")
	List<Product> finPrUser();
	
	@Query("SELECT entity FROM Product entity WHERE available=1")
	List<Product> finLimit(Pageable pageable);
	
	@Query("SELECT entity FROM Product entity WHERE available=1 ORDER BY entity.price DESC")
	List<Product> finLimitPriceDESC(Pageable pageable);
	
	@Query("SELECT entity FROM Product entity WHERE available=1 ORDER BY entity.price ASC")
	List<Product> finLimitPriceASC(Pageable pageable);
	
	@Query("SELECT entity FROM Product entity WHERE available=1 ORDER BY entity.name ASC")
	List<Product> finLimitNameASC(Pageable pageable);
	
	@Query("SELECT entity FROM Product entity WHERE entity.cartdetail.cart.id = :id")
	List<Product> finByIdCard(@Param("id") Integer id);
	
	@Query("SELECT entity FROM Product entity WHERE entity.name LIKE %:keyword% ")
	List<Product> finByKeyword(@Param("keyword") String keyword);
	
	@Query("SELECT e FROM Product e WHERE e.promotiondetail.promotion.id = :id")
	List<Product> finByIdPromotion(@Param("id") Integer id);
	
	@Query("SELECT e FROM Product e ")
	List<Product> finByIdPromotionNot();
}
