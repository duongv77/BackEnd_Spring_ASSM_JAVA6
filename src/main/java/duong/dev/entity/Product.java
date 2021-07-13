package duong.dev.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity
@Component
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "create_date")
	private String createdate;
	
	@Column(name = "available")
	private Integer available;
	
	@Column(name = "image")
	private String image;
	
	@OneToMany(mappedBy = "product")
	private List<Orderdetail> orderdetal;
	
	@ManyToOne
	@JoinColumn(
			name = "productype_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Productype productype;
	
	@OneToOne(mappedBy = "product")
	private Cartdetail cartdetail;
	
}
