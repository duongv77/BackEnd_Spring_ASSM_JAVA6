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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Table(name = "cartdetails")
public class Cartdetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(
			name = "cart_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Cart cart;

	@Column(name = "create_date")
	private String createdate;
	
	@OneToOne
	@JoinColumn(
			name = "product_id",
			nullable = false,
			referencedColumnName = "id"
		)
	private Product product;
	
	@Column(name = "quantity")
	private Integer quantity;
}
