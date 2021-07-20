package duong.dev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Table(name = "promotiondetails")
public class Promotiondetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(
			name = "promotion_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Promotion promotion;
	
	@OneToOne
	@JoinColumn(
			name = "product_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Product product;
}
