package com.itwill.dto;

import java.util.List;

import com.itwill.entity.Userinfo;
import com.itwill.entity.Wish;

import lombok.Builder;

@Builder
public class WishlistDto {
	
	private List<Wish> wishList;

}
