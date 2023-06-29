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

public class ProductSearchAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("search_product");
		ProductDAO pDao = ProductDAO.getInstance();
		request.setAttribute("searchName", name);
		
		// 1. 화면전환 시에 조회하는 페이지번호 and 화면에 그려질 데이터개수 2개를 전달받음
		// 첫 페이지 경우
		int pageNum = 1;
		int amount = 20;
			
		// 페이지번호를 클릭하는 경우
		if(request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		
		List<ProductVO> list = pDao.productSearch("%"+name+"%", pageNum, amount);
		int searchCount = pDao.productSearchCount("%"+name+"%"); // 검색한 상품 수

		// 검색한 데이터가 존재할 때
		if(list.size() != 0) {
			PageVO pageVO = new PageVO(pageNum, amount, searchCount);
					
			// 3. 페이지네이션을 화면에 전달
			request.setAttribute("pageVO", pageVO);
					
			// 화면에 가지고 나갈 list를 request에 저장
			request.setAttribute("searchList", list);
			request.setAttribute("searchCount", searchCount);
			
			String url = "/product/productSearch.jsp";
			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else if(list.size() == 0) {
			String url = "/product/productSearchNull.jsp";
			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		}
		
		
//		List<ProductVO> list = pDao.productSearch("%"+name+"%");
//		int searchCount = pDao.productSearchCount("%"+name+"%");
//
//		request.setAttribute("searchName", name);
//
//		if(list.size() != 0) {
//			request.setAttribute("searchList", list);
//			request.setAttribute("searchCount", searchCount);
//			
//			String url = "/product/productSearch.jsp";
//			RequestDispatcher dis = request.getRequestDispatcher(url);
//			dis.forward(request, response);
//			
//		}else if(list.size() == 0) {
//			String url = "/product/productSearchNull.jsp";
//			RequestDispatcher dis = request.getRequestDispatcher(url);
//			dis.forward(request, response);
//		}
		
		
	}

}
