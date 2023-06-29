package com.ezen.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.ezen.vo.ProductVO;

import util.DBManager;


public class ProductDAO {
	private static ProductDAO instance = new ProductDAO();
	
	private ProductDAO() {}
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	// 데이터 크롤링해서 list에 받아서 return, list에 저장하면서 DB에도 저장
	public List<ProductVO> productAllAdd() {	
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into productlist values (?,?,?,?,?,?)";
		
		try {
			int k = 1;
			for(int i=1; i<=9; i++) {
				Document doc = Jsoup.connect("http://www.theskinfood.com/shop/shopbrand.html?type=P&xcode=019&sort=&page="+i).get();
				Elements images = doc.select("img.MS_prod_img_l");
				Elements name = doc.select("span.goods_name");
				Elements price = doc.select("span.sale_price");
				Elements link = doc.select("ul.goodsDisplayTextWrap li a");
				
				for(int j=0; j<images.size(); j++) {
					try {
//						System.out.println("번호 : " + k);
//						System.out.println("images : "	+ images.get(j).attr("src"));
//						System.out.println("name : " + name.get(j).text());
//						System.out.println("price : " + price.get(j).text());

						// 각 상품당 클릭시 상세페이지로 넘어가는 링크
						Document doc2 = Jsoup.connect("http://www.theskinfood.com"+link.get(j).attr("href")).get();
		                  
						// 상세페이지에서 태그 긁어오기
		                Elements tag = doc2.select("span.product-info__item-content--tag a");
		                
		                int review = (int)(Math.random()*300+1); 
		                
						ProductVO vo = new ProductVO();
						vo.setCode(k);
						vo.setImage("https://www.theskinfood.com/" + images.get(j).attr("src"));
						vo.setName(name.get(j).text());
						vo.setPrice(price.get(j).text().substring(2).replace(",", "")); // DB에 number로 넣기 위해 통화기호랑 , 잘라내기
						vo.setTag(tag.text());
						vo.setReview(review);
						
						list.add(vo);
						
						conn = DBManager.getConnection();
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, vo.getCode());
						pstmt.setString(2, vo.getName());
						pstmt.setInt(3, Integer.parseInt(vo.getPrice()));
						pstmt.setString(4, vo.getImage());
						pstmt.setString(5, " " + vo.getTag());
						pstmt.setInt(6, vo.getReview());
						
						pstmt.executeUpdate();
					
						k++;
						
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						try {
							DBManager.close(conn, pstmt);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}catch(Exception e) {
		e.printStackTrace();
		}
		return list;
	}
	
	// 데이터 전부 선택
	public List<ProductVO> productAllSelect() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from productlist order by id";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setCode(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setPrice(""+rs.getInt("price"));
				vo.setImage(rs.getString("image"));
				vo.setTag(rs.getString("tag"));
				vo.setReview(rs.getInt("review"));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	// 데이터 1건 추출
	public ProductVO selectOneProductByNum(int num) {
		ProductVO vo= null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from productlist where id = ?";
		
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new ProductVO();
				vo.setCode(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setPrice(""+rs.getInt("price"));
				vo.setImage(rs.getString("image"));
				vo.setTag(rs.getString("tag").replace(" ", " #"));
				vo.setReview(rs.getInt("review"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	// 데이터 개수 가져오기
	public int countProduct() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from productlist";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	// 페이징, 20개씩 추출
	public List<ProductVO> getProductList(int pageNum, int amount){
		List<ProductVO> list = new ArrayList<ProductVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, A.*"
				+ "from(select * from productlist order by id) A)"
				+ "where rn > ? and rn <= ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum-1)*amount);
			pstmt.setInt(2, pageNum*amount);	
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setCode(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setPrice(""+rs.getInt("price"));
				vo.setImage(rs.getString("image"));
				vo.setTag(rs.getString("tag").replace(" ", " #"));
				vo.setReview(rs.getInt("review"));
				
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 이름으로 검색, 페이징 처리 위해 20개씩 추출
	public List<ProductVO> productSearch(String name, int pageNum, int amount){
		List<ProductVO> list = new ArrayList<ProductVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, A.*"
				+ "from(select * from productlist where name like ?) A)"
				+ "where rn > ? and rn <= ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, (pageNum-1)*amount);
			pstmt.setInt(3, pageNum*amount);	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setCode(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setPrice(""+rs.getInt("price"));
				vo.setImage(rs.getString("image"));
				vo.setTag(rs.getString("tag").replace(" ", " #"));
				vo.setReview(rs.getInt("review"));
		
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 이름으로 검색하고 결과 건수 추출
	public int productSearchCount(String name) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from productlist where name like ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	
}