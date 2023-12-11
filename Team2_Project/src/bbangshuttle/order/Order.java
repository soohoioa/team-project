package bbangshuttle.order;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


/**
 VO(Value Object),DTO(Data Transfer Object)
	- orders 테이블 1개 row의 데이타의 값을 가지는객체
	- orders 테이블 1개 row의 데이타값을 이동(파라메타,리턴데이타)시키기위한객체 
	- orders 테이블의 컬럼과 동일한수의 멤버변수를가지는객체

*/
public class Order {
	
	private int o_no; // 주문번호
	private Date o_date; // 주문날짜
	private int o_price; // 주문가격
	private String o_desc; // 주문설명
	
	/*************FK****************/
	private String member_id; // 멤버아이디
	
	/***********List<OrderItem>****/
	/* Order 1 : OrderItem N */
	private List<OrderItem> orderItemList;
	
	public Order() {
		orderItemList = new ArrayList<OrderItem>();
	}

	public Order(int o_no, Date o_date, int o_price, String o_desc, String member_id, List<OrderItem> orderItemList) {
		super();
		this.o_no = o_no;
		this.o_date = o_date;
		this.o_price = o_price;
		this.o_desc = o_desc;
		this.member_id = member_id;
		if(orderItemList != null) {
			this.orderItemList = orderItemList;
		} else {
			this.orderItemList = new ArrayList<OrderItem>();
		}
		
	}

	public int getO_no() {
		return o_no;
	}

	public void setO_no(int o_no) {
		this.o_no = o_no;
	}

	public Date getO_date() {
		return o_date;
	}

	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}

	public int getO_price() {
		return o_price;
	}

	public void setO_price(int o_price) {
		this.o_price = o_price;
	}

	public String getO_desc() {
		return o_desc;
	}

	public void setO_desc(String o_desc) {
		this.o_desc = o_desc;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	@Override
	public String toString() {
		return "Order [o_no=" + o_no + ", o_date=" + o_date + ", o_price=" + o_price + ", o_desc=" + o_desc
				+ ", member_id=" + member_id + ", orderItemList=" + orderItemList + "]";
	}
	
	
	
}
