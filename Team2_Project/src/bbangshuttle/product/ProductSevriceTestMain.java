package bbangshuttle.product;

public class ProductSevriceTestMain {
	
	public static void main(String[] args)throws Exception{
		
		ProductService productSevrice = new ProductService();
		System.out.println("------------- 검색 -----------");
		
		System.out.println(productSevrice.ProductFindByNo(10));
		System.out.println("------------- 전체검색 -----------");
		
		System.out.println(productSevrice.ProductFindByAll());
		
		System.out.println("-------------상품 이름------------");
		
		System.out.println(productSevrice.ProductFindByKetword("촉촉한쵹호칩"));
		
		System.out.println("------------조회수 상품-------------");
		
		System.out.println(productSevrice.productCountUpdate(10));

		System.out.println("---------------- 1번 ------------");

		System.out.println(productSevrice.productCategoryAll(1));
		
		System.out.println("--------------- 2번 ---------------");
		
		System.out.println(productSevrice.productCategoryAll(2));
		
		System.out.println("------------------------------");
		
		
		
		
	}
}
