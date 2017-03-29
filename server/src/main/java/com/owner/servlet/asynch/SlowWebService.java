package com.owner.servlet.asynch;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebListener;

@WebListener
public class SlowWebService implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		Queue<AsyncContext> jobQueue = new ConcurrentLinkedQueue<AsyncContext>();
		sce.getServletContext().setAttribute("slowWebServiceJobQueue", jobQueue);
		// pool size matching Web services capacity
		Executor executor = Executors.newFixedThreadPool(200);

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					if (!jobQueue.isEmpty()) {
						final AsyncContext aCtx = jobQueue.poll();
						executor.execute(new Runnable() {

							/*
							 * (non-Javadoc)
							 * 
							 * @see java.lang.Runnable#run()
							 */
							public void run() {
								ServletRequest request = aCtx.getRequest();
								// get parameteres
								// invoke a Web service endpoint
								// set results
								try {
									Thread.currentThread().sleep(5000L);
									aCtx.getResponse().getWriter().write(Thread.currentThread().getName());
									aCtx.getResponse().getWriter().close();
								} catch (IOException | InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					}

				}
			}
		}).start();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
