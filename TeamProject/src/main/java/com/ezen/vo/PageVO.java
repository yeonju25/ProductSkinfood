package com.ezen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVO {
	private int startPage; 		// 게시글 화면 첫 번째 번호
	private int endPage;		// 게시글 화면 마지막 번호
	private int pageNum;		// 현재 조회하는 페이지 번호
	private int amount = 20;	// 화면에 그려질 데이터 수 
	private int count;		// 전체 게시글 수 
	
	public PageVO(int pageNum, int amount, int count) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.count = count;
		
		
		
		// endPage 결정, 공식 = (int)Math.ceil(페이지번호/페이지네이션 개수) * 페이지네이션 개수
		this.endPage = (int)Math.ceil(this.pageNum * 0.1) * 10;
		
		// startPage 결정, 공식 = 끝페이지 - 페이지네이션 개수 + 1
		this.startPage = this.endPage - 10 + 1;
		
		// realEnd(진짜 끝번호) 구해서 endPage의 값을 다시 결정
		int realEnd = (int)Math.ceil(this.count / (double)this.amount);
		
		if(this.endPage > realEnd) {
			this.endPage = realEnd;
		}
		System.out.println("시작페이지:" + this.startPage + ", 끝페이지:" + this.endPage + ", count: "+count);
		
		// productListAction에서 PageVO 계산처리 코드작성..
	}	
}
