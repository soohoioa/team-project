package bbangshuttle.product;

import bbangshuttle.product.ProductDao;
public class ProductDaoTestMain {

	public static void main(String[] args) throws Exception {

		ProductDao productDao = new ProductDao();
		
		System.out.println("---------1.상품번호로 검색-------");
		
		System.out.println(productDao.findByProductNo(1));
		
		System.out.println("---------2.상품전체 출력-------");
		System.out.println(productDao.findAll());
		System.out.println();
		System.out.println("---------3.키워드로 검색-------");
		System.out.println(productDao.findByKeyword("라떼"));
		System.out.println("---------4.카운트증가-------");
		System.out.println(productDao.updateViewCount(3));
		
	}

}
