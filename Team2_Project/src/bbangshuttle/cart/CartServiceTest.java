package bbangshuttle.cart;

public class CartServiceTest {

	public static void main(String[] args) throws Exception {

		CartService cs = new CartService();
		//cart add
		System.out.println("1. add cart");
		int rowCount = cs.addCart("leeshuttle22", 1, 2);
		System.out.println(">>>"+rowCount +" 개 추가 성공");
		//특정상품 특정회원의 수량을 몇개로 변경
		System.out.println("2. update");
		System.out.println(cs.updateCart(1, 6)+"번 장바구니 수량변경");
		
		System.out.println("3. find member item cart number");
		System.out.println(cs.getCartItemByCartNo(2));
		
		System.out.println("4. show member cart");
		System.out.println(cs.getCartItemByUserId("leeshuttle22"));
		
		System.out.println("5. delete member cart item");
		System.out.println("삭제"+cs.deleteCartItemByCartNo(2));
		
		System.out.println("5. delete member cart All");
		System.out.println(cs.deleteCartItemByUserId("parkshuttle33"));
	}

}
