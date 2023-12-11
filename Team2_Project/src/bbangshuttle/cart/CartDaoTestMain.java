package bbangshuttle.cart;

import java.util.List;

import bbangshuttle.product.Product;



public class CartDaoTestMain  {
	
	public static void main(String[] args)throws Exception {
		CartDao cartDao=new CartDao();
		System.out.println("1.add(insert)");
		Cart addCart=new Cart(0,1,
								"leeshuttle22",
								new Product(3, null, null, null, 0, 0, 0)
								);
		int rowCount=-999;
		/*
		rowCount = cartDao.add(addCart);
		System.out.println(">> "+rowCount);
		*/
		System.out.println("2.updateByCartNo");
		rowCount=cartDao.updateByCartNo(8,5);
		System.out.println(">> "+rowCount);
		
		System.out.println("2.updateByProductNoAndUserId");
		rowCount=cartDao.updateByProductNo("leeshuttle22",3,2);
		System.out.println(">> "+rowCount);
		
		System.out.println("3.delete");
		rowCount=cartDao.deleteByCartNo(8);
		System.out.println(">> "+rowCount);
		System.out.println("4.cartList[select]");
		List<Cart> cartList1=cartDao.findByMember_id("leeshuttle22");
		System.out.println("leeshuttle22-->"+cartList1);
//		List<Cart> cartList2=cartDao.findByMember_id("leeshuttle22");
//		System.out.println("guard2-->"+cartList2);
//		List<Cart> cartList3=cartDao.findByMember_id("leeshuttle22");
//		System.out.println("guard3-->"+cartList3);
		System.out.println("5.selectProductCount");
		int productCount1=cartDao.countByProductNo("leeshuttle22",1 );
		System.out.println(">> "+productCount1);
		int productCount2=cartDao.countByProductNo("leeshuttle22",3 );
		System.out.println(">> "+productCount2);
	}
}
