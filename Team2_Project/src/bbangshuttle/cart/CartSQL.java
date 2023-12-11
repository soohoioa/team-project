package bbangshuttle.cart;
/*
 * sql ex) public final static String SELECT_XX="";
 */
public class CartSQL {
	public static final String CART_INSERT = "insert into cart(cart_no, member_Id, p_no, cart_qty) values(cart_cart_no_SEQ.nextval,?,?,?)";
	public static final String CART_SELECT_BY_MEMBER_ID = "select c.*,p.* from cart c join product p on c.p_no=p.p_no where member_id=?";
	public static final String CART_SELECT_BY_CART_NO = "select * from cart c join product p on c.p_no=p.p_no where cart_no=?";
	public static final String CART_COUNT_BY_USERID_PRODUCT_NO = "select count(*) as p_count from cart where member_id=? and p_no=?";
	public static final String CART_DELETE_BY_CART_NO = "delete from cart where cart_no=?";
	public static final String CART_DELETE_BY_MEMBER_ID = "delete from cart where member_id=?";
	public static final String CART_UPDATE_BY_CART_NO = "update cart set cart_qty=? where cart_no=?";
	public static final String CART_UPDATE_BY_PRODUCT_NO_MEMBER_ID = "update cart set cart_qty=cart_qty+? where member_id=? and p_no=?";
	
}
