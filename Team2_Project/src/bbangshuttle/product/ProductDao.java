package bbangshuttle.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbangshuttle.common.DataSource;

public class ProductDao {
	private DataSource dataSource;
	public ProductDao() throws Exception{
		dataSource=new DataSource();
	}
	/*
	 * 상품번호로 검색.
	 */
	public Product findByProductNo(int p_no) throws Exception {
		Product product= null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_NO);
		pstmt.setInt(1, p_no);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			product = new Product(
					rs.getInt("p_no"),
					rs.getString("p_name"), 
					rs.getString("p_desc"),
					rs.getString("p_image"), 
					rs.getInt("p_price"),
					rs.getInt("p_view_count"),
					rs.getInt("p_category")
					);
		}
		return product;
	}
	/*
	 * 상품 전체 출력
	 */
	public List<Product> findAll() throws Exception{
		List<Product> productList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SELECT_ALL);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			productList.add(new Product(
					rs.getInt("p_no"),
					rs.getString("p_name"), 
					rs.getString("p_desc"),
					rs.getString("p_image"), 
					rs.getInt("p_price"),
					rs.getInt("p_view_count"),
					rs.getInt("p_category")));
		}
		return productList;
	}
	
	/*
	 * 키워드로 검색
	 */
	
	public List<Product> findByKeyword(String keyword) throws Exception{
		List<Product> productList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_KEYWORD);
		pstmt.setString(1, keyword);
		pstmt.setString(2, keyword);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			productList.add(new Product(
					rs.getInt("p_no"),
					rs.getString("p_name"), 
					rs.getString("p_desc"),
					rs.getString("p_image"), 
					rs.getInt("p_price"),
					rs.getInt("p_view_count"),
					rs.getInt("p_category")));
		}
		return productList;
	}
	/*
	 * 카운트증가
	 */
	
	public int updateViewCount(int p_no) throws Exception {
		
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_UPDATE_COUNT);
		pstmt.setInt(1, p_no);
		
		int rowCount = 0;
		rowCount =pstmt.executeUpdate();
		
		return rowCount;
		
		
	}
	// 카테고리 전체출력
	public List<Product> productCategoryList(int p_catehorey) throws Exception{
		List<Product> productList = new ArrayList<>(); 
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SELECT_CATEGORY_ALL);
		pstmt.setInt(1, p_catehorey);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			productList.add(new Product(
					rs.getInt("p_no"),
					rs.getString("p_name"), 
					rs.getString("p_desc"),
					rs.getString("p_image"), 
					rs.getInt("p_price"),
					rs.getInt("p_view_count"),
					rs.getInt("p_category")));
		}
		return productList;
	}
	
}
