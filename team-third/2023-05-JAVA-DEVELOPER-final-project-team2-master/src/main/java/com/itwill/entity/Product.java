package com.itwill.entity;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.Bag;

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
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@SequenceGenerator(name = "Product_product_no_SEQ",sequenceName = "Product_product_no_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Product_product_no_SEQ")
	private Long productNo;
	private String productName;
	private Integer productPrice;
	private String productCategory;
	private String productPetCategory;
	private Integer productQty;
	
	// 제품 대표이미지
	private String productImage;
	// 제품 상세이미지
	private String productDetailImage;
	
	private Double productStarAvg;

}
