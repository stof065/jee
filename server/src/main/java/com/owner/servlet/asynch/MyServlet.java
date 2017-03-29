package com.owner.servlet.asynch;

import java.io.IOException;
import java.util.Queue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class MyServlet.
 */
@WebServlet(name = "myServlet", urlPatterns = { "/slowprocess" }, asyncSupported = true)
public class MyServlet extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		AsyncContext aCtx = request.startAsync(request, response);
		try {
			response.getWriter().write("server-thread :" + Thread.currentThread().getName() + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletContext appScope = request.getServletContext();
		((Queue<AsyncContext>) appScope.getAttribute("slowWebServiceJobQueue")).add(aCtx);
	}

	/**
	 * The Class SlowWebService.
	 */

}