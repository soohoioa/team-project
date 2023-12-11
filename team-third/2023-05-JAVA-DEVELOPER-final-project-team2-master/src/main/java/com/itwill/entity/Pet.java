package com.itwill.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.CreationTimestamp;

import com.itwill.dto.PetDto;

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
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Pet {
	
	
	@SequenceGenerator(name = "Pet_pet_no_SEQ",sequenceName = "Pet_pet_no_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Pet_pet_no_SEQ")
	@Id
	 private Long petNo;
	 private String petLocal;
	 private String petType;
	 private String petGender;
	 
	 @CreationTimestamp
	 private Date  petRegisterDate;
	 private String petFindPlace;
	 private String petCharacter;
	 private String petImage;
	 
	 @ManyToOne(cascade = CascadeType.PERSIST ,fetch = FetchType.LAZY)
	 @JoinColumn(name = "center_no")
	 @ToString.Exclude
	 private Center center;
	 
	 

}
