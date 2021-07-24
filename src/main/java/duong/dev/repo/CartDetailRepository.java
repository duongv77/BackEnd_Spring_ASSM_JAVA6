package duong.dev.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Cartdetail;

@Repository
public interface CartDetailRepository extends JpaRepository<Cartdetail, Integer> {
	
	
	@Query("SELECT entity FROM Cartdetail entity WHERE entity.cart.id = :id")
	List<Cartdetail> finByIdCard(@Param("id") Integer id);
	
	@Query("SELECT entity FROM Cartdetail entity WHERE id = :id")
	public Cartdetail finByIdCarddetail(@Param("id") Integer id);
	
	@Query("SELECT entity FROM Cartdetail entity WHERE entity.product.id = :id")
	public Cartdetail finByIdProduct(@Param("id") Integer id);
	
	@Query("SELECT entity FROM Cartdetail entity WHERE entity.cart.user.id = :id")
	public List<Cartdetail> finByIdUser(@Param("id") Integer id);
	
	@Query("DELETE FROM Cartdetail WHERE id = :id")
	public void deleteByIdCustom(@Param("id") Integer id);
}
