package duong.dev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Promotiondetail;

@Repository
public interface PromotionDetaiRepository extends JpaRepository<Promotiondetail, Integer>{

}
