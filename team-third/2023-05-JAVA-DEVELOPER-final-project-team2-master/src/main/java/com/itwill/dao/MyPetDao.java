package com.itwill.dao;

import java.util.List;

import com.itwill.entity.MyPet;

public interface MyPetDao {

     public MyPet CreatePet(MyPet myPet);
     
     public void DeletePet(Long mypetNo);
     
     public MyPet UpdatePet(MyPet myPet);
     
     public List<MyPet> findAll();
     
     public MyPet findByNo(Long mypetNo);
    
     public List<MyPet> findMyPetListByuserNo(Long userNo);
   
     public void deleteMypetByUserNo(Long mypetNo);
     
     public void deleteMypetAllByUserNo(Long userNo);
     
     //대표 동물 뽑아오기 
     public MyPet findLeaderMyPet(Long userNo);
     
     
}