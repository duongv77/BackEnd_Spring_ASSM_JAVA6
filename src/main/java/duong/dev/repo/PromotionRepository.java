package duong.dev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer>{
	
}
