package com.ezen.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.ProductDAO;
import com.ezen.vo.PageVO;
import com.ezen.vo.ProductVO;

public class ProductListAction implements Action{

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO pDao = ProductDAO.getInstance();
		List<ProductVO> list = pDao.productAllSelect();
		
		// DB에 저장된 데이터가 없으면 크롤링한 내용을 DB에 저장
		if(list.size() == 0) {
			list = pDao.productAllAdd();
		}
		
		
		// 1. 화면전환 시에 조회하는 페이지번호 and 화면에 그려질 데이터개수 2개를 전달받음
		// 첫 페이지 경우
		int pageNum = 1;
		int amount = 20;
			
		// 페이지번호를 클릭하는 경우
		if(request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
				
		// 2. pageVO생성
		list = pDao.getProductList(pageNum, amount);
		int count = pDao.countProduct(); // 전체 상품 수
		PageVO pageVO = new PageVO(pageNum, amount, count);
				
		// 3. 페이지네이션을 화면에 전달
		request.setAttribute("pageVO", pageVO);
				
		// 화면에 가지고 나갈 list를 request에 저장
		request.setAttribute("productList", list);
		
		
		String url = "/product/productList.jsp";
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}	



