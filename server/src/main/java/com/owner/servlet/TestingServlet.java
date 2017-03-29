package com.owner.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.ejb.UserDao;

@WebServlet(urlPatterns = "/test")
public class TestingServlet extends HttpServlet {

	@EJB
	UserDao dao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7522472976201804810L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.getWriter().write(dao.findAll().toString());

	}

}
