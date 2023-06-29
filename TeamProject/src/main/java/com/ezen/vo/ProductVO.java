package com.ezen.vo;
import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
public class ProductVO {
	private int code, review;
	private String image, name, price, tag;
}
