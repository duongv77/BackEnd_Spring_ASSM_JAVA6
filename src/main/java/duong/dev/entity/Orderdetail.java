package duong.dev.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "orderdetails")
public class Orderdetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name = "price")
	private Integer price;
	
	@OneToOne
	@JoinColumn(
		name = "oder_id",
		nullable = false,
		referencedColumnName = "id"
	)			
	private Order order;
	
	@OneToOne
	@JoinColumn(
			name = "product_id",
			nullable = false,
			referencedColumnName = "id"
		)
	private Product product;
}
