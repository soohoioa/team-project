package bbangshuttle.product;

import java.util.List;


public class ProductService {
	private ProductDao productDao;
	
	public ProductService() throws Exception {
		this.productDao = new ProductDao();
	}
	//상품 번호로 검색
	public Product ProductFindByNo(int p_no)throws Exception {
		
		return productDao.findByProductNo(p_no);
	}
	// 상품 전체 출력
	public List<Product> ProductFindByAll() throws Exception{
		
		return productDao.findAll();
	}
	//키워드로 검색
	public List<Product> ProductFindByKetword(String keyword)throws Exception{
			
		return productDao.findByKeyword(keyword);
	}
	//카운트증가
	public int productCountUpdate(int p_no) throws Exception{
			
		return productDao.updateViewCount(p_no);
	}
		
	//카테고리 전체 검색
	public List<Product> productCategoryAll (int p_category) throws Exception {
			
		return productDao.productCategoryList(p_category);
	}
}
