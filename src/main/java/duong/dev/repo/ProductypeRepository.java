package duong.dev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Productype;

@Repository
public interface ProductypeRepository extends JpaRepository<Productype, Integer> {
}
