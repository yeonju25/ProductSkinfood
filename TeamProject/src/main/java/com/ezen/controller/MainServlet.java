package com.ezen.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.action.Action;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String command = request.getParameter("command");
		System.out.println("command : " + command);
		
		ActionFactory af = ActionFactory.getInstance();
		
		Action action = af.getAction(command);
		
		if(action != null)
			action.execute(request, response);

/*
		HttpServletRequest request =  (HttpServletRequest) req;
		HttpServletResponse response =  (HttpServletResponse) resp;

		System.out.println("URI path 전체보기");
		System.out.println("Context path : " + request.getContextPath());
		System.out.println("URI : " + request.getRequestURI());
		System.out.println("URL : " + request.getRequestURL());
		System.out.println("-----------------------------------------------");

		//URI만 파싱
		String endPoint = request.getRequestURI().replaceAll(request.getContextPath(),"" );
		System.out.println("endPoint : " + endPoint);
		
		
		
		
		response.setContentType("text/html");
		//		String url = "main/main.jsp";
		//	//	url = "DBTest.jsp";
		//		RequestDispatcher dis = request.getRequestDispatcher(url);
		//		dis.forward(request, response);
		*/
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
