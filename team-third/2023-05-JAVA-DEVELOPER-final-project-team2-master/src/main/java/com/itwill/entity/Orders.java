package com.itwill.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.itwill.dto.OrderItemDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orders {
	@Id
	@SequenceGenerator(name = "Orders_order_no_SEQ",sequenceName = "Orders_order_no_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Orders_order_no_SEQ")
	private Long orderNo;
	@CreationTimestamp
	private Date orderDate;
	private Integer orderPrice;
	private String orderAddress;
	private String orderDesc;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Builder.Default
	@JoinColumn(name = "user_no")
	@ToString.Exclude
	private Userinfo userinfo = new Userinfo();
	
	@OneToMany(mappedBy = "orders",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@Builder.Default
	@ToString.Exclude
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

}
