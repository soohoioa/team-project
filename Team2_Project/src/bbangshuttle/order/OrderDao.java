package bbangshuttle.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import bbangshuttle.common.DataSource;
import bbangshuttle.product.Product;

public class OrderDao {
	
	private DataSource dataSource;
	// 카트, 상품도 추가
	
	public OrderDao() throws Exception {
		dataSource = new DataSource();
	}
	
	// 주문 전체 삭제
	public int deleteByMemberId(String member_Id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(OrderSQL.ORDER_DELETE_BY_MEMBER_ID);
			pstmt.setString(1, member_Id);
			rowCount = pstmt.executeUpdate();
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}	
		return rowCount;
	}
	
	
	// 주문 한건 삭제 
	public int daleteByOrderNo(int o_no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(OrderSQL.ORDER_DELETE_BY_O_NO);
			pstmt.setInt(1, o_no);
			rowCount = pstmt.executeUpdate();
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}
		
		return rowCount;
	}
	
	
	// 주문 생성
	public int insert(Order order) throws Exception {
		/*
		insert into orders(o_no,o_date,o_price,o_desc,member_id) 
		VALUES (orders_o_no_SEQ.nextval, sysdate-2, 5000, '따뜻한 아이스 아메리카노 외0종', 
				'parkshuttle33');
		
		insert into order_item(oi_no, oi_qty, o_no, p_no) 
		VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 9);

		 */
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt1 = con.prepareStatement(OrderSQL.ORDER_INSERT);
			pstmt1.setInt(1, order.getO_price());
			pstmt1.setString(2, order.getO_desc());
			pstmt1.setString(3, order.getMember_id());
			pstmt1.executeUpdate();
			
			pstmt2 = con.prepareStatement(OrderSQL.ORDERITEM_INSERT);
			for(OrderItem orderItem : order.getOrderItemList()) {
				pstmt2.setInt(1, orderItem.getOi_qty());
				pstmt2.setInt(2, orderItem.getProduct().getP_no());
				pstmt2.executeUpdate();
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}
		return 0;		
	}
	
	
	// 주문 전체(특정 사용자)
	public List<Order> findOrderByMemberId(String member_id)throws Exception {
		ArrayList<Order> orderList = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			/*
			 * select * from orders where member_id = 'kimshuttle11';
			 */
			pstmt = con.prepareStatement(OrderSQL.ORDER_SELECT_BY_MEMBER_ID);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				/*
				 	private int o_no; // 주문번호
					private Date o_date; // 주문날짜
					private int o_price; // 주문가격
					private String o_desc; // 주문설명

					private String member_id; // 멤버아이디
				 */
				orderList.add(new Order(rs.getInt("o_no"), rs.getDate("o_date"), 
								rs.getInt("o_price"), rs.getString("o_desc"), 
								rs.getString("member_id"), null));
			}
			
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return orderList;
	}
	
	
	/*
	 * 주문 + 주문아이템들 전체 (특정사용자)
	 */
	
	public List<Order> findOrderWithOrderItemMemberId(String member_Id) throws Exception {
		
		List<Order> orderList = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		try {
			con = dataSource.getConnection();
			/*
			 *  "select * from orders where member_id=?"
			 */
			pstmt1 = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_MEMBER_ID);
			pstmt1.setString(1, member_Id);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				orderList.add(new Order(rs1.getInt("o_no"), rs1.getDate("o_date"), 
						rs1.getInt("o_price"), rs1.getString("o_desc"), 
						rs1.getString("member_id"), null));
			}
			
			
			pstmt2 = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_O_NO);
			
			for (int i = 0; i < orderList.size(); i++) {
				Order tempOrder = orderList.get(i);		
				/*
				 ORDER_SELECT_WITH_PRODUCT_BY_MEMBER_ID = 
				 "select * from orders o join order_item oi on o.o_no = oi.o_no  
				 join  product p on oi.p_no = p.p_no where o.member_id = ? and o.o_no = ?";
				 */
				pstmt2.setInt(1, tempOrder.getO_no());
				rs2 = pstmt2.executeQuery();
				/*
				O_NO, O_DATE,  O_PRICE, O_DESC, 			MEMBER_ID,   OI_NO, OI_QTY, O_NO_1, P_NO, P_NO_1, 			P_NAME, P_PRICE, 	P_IMAGE,				 P_DESC, 								P_VIEW_COUNT, P_CATEGORY
				 11	2023/07/19	44000	촉촉한쵹호칩 외 2종	kimshuttle11	19		1		11		1		1	촉촉한쵹호칩	12000		/images/chocchoc.jpg	너무나 쵹쵹한것..						123				1
				 11	2023/07/19	44000	촉촉한쵹호칩 외 2종	kimshuttle11	20		1		11		3		3	오카상 크로와상	140000		/images/mom.jpg			어머니의 손맛이 담긴 오카상~ 크로와상~	527				1
				 11	2023/07/19	44000	촉촉한쵹호칩 외 2종	kimshuttle11	21		1		11		4		4	패스트 패스트리	18000		/images/pest.jpg		감질맛 나는것이 역병 그잡채				612				1
				 *
				 */
				Order orderWithOrderItem = null;
				if (rs2.next()) {
					orderWithOrderItem = new Order(	rs2.getInt("o_no"), 
													rs2.getDate("o_date"), 
													rs2.getInt("o_price"), 
													rs2.getString("o_desc"), 
													rs2.getString("member_id"), null);
					do {
						orderWithOrderItem.getOrderItemList()
						.add(new OrderItem(	rs2.getInt("oi_no"), 
											rs2.getInt("oi_qty"), 
											rs2.getInt("o_no"),
								new Product(rs2.getInt("p_no"), 
											rs2.getString("p_name"), 
											rs2.getString("p_desc"), 
											rs2.getString("p_image"), 
											rs2.getInt("p_price"), 
											rs2.getInt("p_view_count"), 
											rs2.getInt("p_category"))));
						} while (rs2.next());	
				}
				orderList.set(i, orderWithOrderItem);
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return orderList;
	}	
	
	
	/*
	 * 주문 1개 보기 (주문 상세 리스트)
	 */
	public Order findByOrderNo(int o_no) throws Exception {
		Order order = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			/*
		 	"select * from orders o join order_item oi on o.o_no = oi.o_no 
		 	join product p on oi.p_no = p.p_no where o.o_no = ?"
			*/
			pstmt = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_O_NO);
			pstmt.setInt(1, o_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				order = new Order(	rs.getInt("o_no"), 
												rs.getDate("o_date"), 
												rs.getInt("o_price"), 
												rs.getString("o_desc"), 
												rs.getString("member_id"), null);
				do {
					order.getOrderItemList()
					.add(new OrderItem(	rs.getInt("oi_no"), 
										rs.getInt("oi_qty"), 
										rs.getInt("o_no"),
							new Product(rs.getInt("p_no"), 
										rs.getString("p_name"), 
										rs.getString("p_desc"), 
										rs.getString("p_image"), 
										rs.getInt("p_price"), 
										rs.getInt("p_view_count"), 
										rs.getInt("p_category"))));
					} while (rs.next());	
			}
		} finally {
			if (con != null) {
				con.close();		
		
			}
		}
		return order;
	}
	
}
