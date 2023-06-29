package com.ezen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ezen.vo.MemberVO;

import util.DBManager;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {}
	
	public static MemberDAO getinstance() {
		return instance;
	}

	public int userCheck(String id, String pass) {
		
		int result = -1;
		String sql = "select pass from member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String getpass = rs.getString("pass");
				
				if(getpass != null && getpass.equals(pass)) {
					result = 1; // 아이디, 비번 일치
				}else {
					result = 0; // id = 일치, pass = 불일치
				}
			}else {
				result = -1; // 존재하지 않는 아이디
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn!= null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public MemberVO getMember(String id) {
		
		MemberVO vo = null;
		
		String sql = "select * from member where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new MemberVO();
				
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setInsertdate(rs.getTimestamp("insertdate"));
				vo.setAdmin(rs.getString("admin"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn!= null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}



}
