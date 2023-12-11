package bbangshuttle.product;
/*
VO(Value Object),DTO(Data Transfer Object)
	- product 테이블 1개 row의 데이타의 값을 가지는객체
	- product 테이블 1개 row의 데이타값을 이동(파라메타,리턴데이타)시키기위한객체 
	- product 테이블의 컬럼과 동일한수의 멤버변수를가지는객체

이름            널?       유형            
------------- -------- ------------- 
P_NO          NOT NULL NUMBER(10)    
P_NAME                 VARCHAR2(50)  
P_PRICE                NUMBER(10)    
P_IMAGE                VARCHAR2(50)  
P_DESC                 VARCHAR2(512) 
P_VIEW_COUNT          NUMBER(10) 
P_CATEGORY				NUMBER(10)   
 */
public class Product {
	private int p_no;
	private String p_name;
	private String p_desc;
	private String p_image;
	private int price;
	private int p_view_count;
	private int p_category;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(int p_no, String p_name, String p_desc, String p_image, int price, int p_view_count,int p_category) {
		
		this.p_no = p_no;
		this.p_name = p_name;
		this.p_desc = p_desc;
		this.p_image = p_image;
		this.price = price;
		this.p_view_count = p_view_count;
		this.p_category = p_category;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_desc() {
		return p_desc;
	}
	public void setP_desc(String p_desc) {
		this.p_desc = p_desc;
	}
	public String getP_image() {
		return p_image;
	}
	public void setP_image(String p_image) {
		this.p_image = p_image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getP_view_count() {
		return p_view_count;
	}
	public void setP_view_count(int p_view_count) {
		this.p_view_count = p_view_count;
	}
	public int getP_category() {
		return p_category;
	}
	public void setP_category(int p_category) {
		this.p_category = p_category;
	}
	@Override
	public String toString() {
		return "Product [p_no=" + p_no + ", p_name=" + p_name + ", p_desc=" + p_desc + ", p_image=" + p_image
				+ ", price=" + price + ", p_view_count=" + p_view_count + ", p_category=" + p_category + "]\n";
	}
	
	
	
}
