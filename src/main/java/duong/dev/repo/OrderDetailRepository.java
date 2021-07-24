package duong.dev.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Orderdetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<Orderdetail, Integer> {
	@Query("SELECT entity FROM Orderdetail entity WHERE entity.order.id = :id")
	public List<Orderdetail> finByIdOrder(@Param("id") Integer id);
}
