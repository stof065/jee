package org.jax.rs;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/services/**")
public class JaxrsServlet {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
