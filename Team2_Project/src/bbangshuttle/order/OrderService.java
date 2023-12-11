package bbangshuttle.order;

import java.util.List;

import bbangshuttle.cart.CartDao;
import bbangshuttle.product.ProductDao;

public class OrderService {
	private OrderDao orderDao;
	private ProductDao productDao;
	private CartDao cartDao;
	public OrderService() throws Exception{
		orderDao = new OrderDao();
		productDao = new ProductDao();
		cartDao = new CartDao();
	}
	
	//주문 전체 삭제
	public int deleteAll(String member_Id)throws Exception {
		return orderDao.deleteByMemberId(member_Id);
	}
	
	//주문 한건 삭제
	public int deleteByOrderNo(int o_no)throws Exception {
		return orderDao.daleteByOrderNo(o_no);
	}
	
	// 주문 목록
	public List<Order> orderList(String member_Id) throws Exception {
		return orderDao.findOrderByMemberId(member_Id);
	}
	
	
	//주문+주문아이템 목록
	public List<Order> orderWithOrderItemList(String member_Id) throws Exception {
		return orderDao.findOrderWithOrderItemMemberId(member_Id);
	}
	
	//주문+주문아이템 상세보기
	public Order orderWithOrderItem(int o_no)throws Exception {
		return orderDao.findByOrderNo(o_no);
	}
	
	
	
	
	
	
	
}

