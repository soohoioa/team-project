package com.itwill.entity;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderItem {

	@Id
	@SequenceGenerator(name = "Order_item_oi_no_SEQ",sequenceName = "Order_item_oi_no_SEQ",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Order_item_oi_no_SEQ")
	private Long oiNo;
	private Integer oiQty;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Builder.Default
	@JoinColumn(name = "order_no")
	@ToString.Exclude
	private Orders orders = new Orders();
	
	@OneToOne(fetch = FetchType.EAGER)
	@Builder.Default
	@JoinColumn(name = "product_no")
	private Product product =new Product();
	
	@OneToOne(fetch = FetchType.EAGER)
	@Builder.Default
	@JoinColumn(name = "os_no")
	private Orderstatus orderStatus = new Orderstatus();

}
