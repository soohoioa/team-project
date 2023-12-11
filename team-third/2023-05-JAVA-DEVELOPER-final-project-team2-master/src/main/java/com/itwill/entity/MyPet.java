package com.itwill.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "mypet")
public class MyPet {
	
	@Id
	@SequenceGenerator(name = "MyPet_mypet_no_SEQ",sequenceName = "MyPet_mypet_no_SEQ",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MyPet_mypet_no_SEQ")
	private Long mypetNo;
	private String mypetName;
	private LocalDateTime mypetBirthday;
	private String mypetKind; //고양이인지 강아지인지 구별
	
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@Builder.Default
	@JoinColumn(name = "user_no")
	@ToString.Exclude
	private Userinfo userinfo = new Userinfo();

	

}
