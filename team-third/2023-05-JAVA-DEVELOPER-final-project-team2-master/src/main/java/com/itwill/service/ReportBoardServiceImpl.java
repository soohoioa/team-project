package com.itwill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.dao.ReportBoardDao;
import com.itwill.entity.ReportBoard;

@Service
@Transactional
public class ReportBoardServiceImpl implements ReportBoardService{

	@Autowired
	private ReportBoardDao reportBoardDao;
	 
	@Override
	public ReportBoard Create(ReportBoard reportBoard) {
		return reportBoardDao.Create(reportBoard);
	}
	
	@Override
	public void deleteById(Long reportBoardNo) {
		reportBoardDao.deleteById(reportBoardNo);
		
	}

	@Override
	public ReportBoard update(ReportBoard reportBoard) {
		return reportBoardDao.update(reportBoard);
	}
	
	@Override
	public List<ReportBoard> findByUserId(String userId) {
		return reportBoardDao.findByUserId(userId);
	}

	@Override
	public List<ReportBoard> findAllByLikeUserId(String userId) {
		return reportBoardDao.findAllByLikeUserId(userId);
	}

	@Override
	public ReportBoard findByBoardNo(Long reportNo) {
		return reportBoardDao.findByBoardNo(reportNo);
	}

	@Override
	public void countReadCount(Long boardNo) {
		reportBoardDao.countReadCount(boardNo);
	}

	@Override
	public List<ReportBoard> findByUserNo(Long userNo) {
		return reportBoardDao.findByUserNo(userNo);
	}

	@Override
	public List<ReportBoard> findAll() {
		
		return reportBoardDao.findAll();
	}
	
	public void saveImageFile(Long recipeId, MultipartFile file) {
	    System.out.println("saveImageFile in ImageService");

	    //Optional<Recipe> recipeOptioanl = recipeRepository.findById(recipeId);

	    //Recipe recipe = null;
	    /*
	    if (recipeOptioanl.isPresent()) {
	      try {
	        recipe = recipeOptioanl.get();
	        Byte[] imageBytes = new Byte[file.getBytes().length];
	        int index = 0;
	        for (byte b: file.getBytes()) 
	          imageBytes[index++]=b;
	        
	        recipe.setImage(imageBytes);
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	      recipeRepository.save(recipe);
	    } else {
	      throw new RuntimeException("Recipe not found with id :: " + recipeId);
	    }
	    */
	  }

	@Override
	public List<ReportBoard> findByBoardNoOrderByBoardNoDesc() {
		return reportBoardDao.findByBoardNoOrderByBoardNoDesc();
	}

	@Override
	public Page<ReportBoard> reportBoardFindAllPage(Pageable pageable) {
		Page<ReportBoard> reportList=reportBoardDao.reportBoardFindAllPage(pageable);
		return reportList;
	}

	@Override
	public List<ReportBoard> findByBoardImage(String boardImage) {
		
		return reportBoardDao.findByBoardImage(boardImage);
	}
	

}
