package com.ezen.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.MemberDAO;
import com.ezen.vo.MemberVO;


public class UserCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "main/login.jsp";
		String id= request.getParameter("id");
		String pass= request.getParameter("pass");
		
		MemberDAO mDao = MemberDAO.getinstance();
		int result = mDao.userCheck(id,pass);
		
		if(result == 1) {
			
			MemberVO mVo= mDao.getMember(id);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mVo);
			
			url = "main.jsp";
			
		}else if (result == 0) {
			// 비번이 틀렸을 때 id값은 그대로 남아있게 하기
			request.setAttribute("id", id);
		}
		
		System.out.println(result);
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}

}
