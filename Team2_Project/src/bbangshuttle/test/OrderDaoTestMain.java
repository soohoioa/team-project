package bbangshuttle.test;

import bbangshuttle.order.OrderDao;

public class OrderDaoTestMain {

	public static void main(String[] args) throws Exception{
		
		OrderDao orderDao = new OrderDao();
		System.out.println(orderDao.deleteByMemberId("hanshuttle155"));
		
		System.out.println(orderDao.daleteByOrderNo(5));
		
		System.out.println(orderDao.findOrderByMemberId("leeshuttle22"));
		
		System.out.println(orderDao.findOrderWithOrderItemMemberId("kimshuttle11"));
		
		System.out.println(orderDao.findByOrderNo(1));
		
		
	}

}
