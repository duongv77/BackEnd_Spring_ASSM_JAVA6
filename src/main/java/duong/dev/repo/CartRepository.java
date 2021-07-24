package duong.dev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	@Query("SELECT entity FROM Cart entity WHERE entity.user.id =:id")
	public Cart finByIdUser(@Param("id") Integer id);
	
}
