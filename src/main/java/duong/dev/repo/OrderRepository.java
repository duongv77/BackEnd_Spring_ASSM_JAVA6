package duong.dev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	@Query("SELECT entity FROM Order entity WHERE id = :id")
	public Order finByIdOrder(@Param("id") Integer id);
	
	@Query("SELECT sum(e.total) FROM Order e WHERE MONTH(e.createDate) = :month and YEAR(e.createDate) = 2021")
	public Integer doanhThuThang(@Param("month") Integer month);
	
//	@Param("month") Integer month
}
