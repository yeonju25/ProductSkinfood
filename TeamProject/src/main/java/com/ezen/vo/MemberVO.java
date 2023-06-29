package com.ezen.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data   //setting,getter,toString
public class MemberVO {
	
	private String id;
	private String pass;
	private String name;
	private String email;
	private String phone;
	private Timestamp insertdate;
	private String admin;

}
