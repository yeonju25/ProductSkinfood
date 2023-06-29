package com.ezen.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.ProductDAO;
import com.ezen.vo.ProductVO;

public class ProductBuyAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("num");
		String amount = request.getParameter("amount");
		
		System.out.println("amount: " + amount);
//		request.setAttribute("amount", amount);
		
		ProductDAO pDao = ProductDAO.getInstance();
		ProductVO vo = pDao.selectOneProductByNum(Integer.parseInt(code));
		
		request.setAttribute("product", vo);
		
		String url = "/product/productBuy.jsp";
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
