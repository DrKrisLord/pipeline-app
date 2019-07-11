/*package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loadLogin")*/
/*public class LoadLoginViewServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {*/
//		String html = "<input type=\"text\" id = \"username\""; 
//		resp.setContentType("text/html");
//		resp.getWriter().write(html);*/
		/*	
		 * FORWARD
		 * here, we use the request object's request dispatcher 
		 * to forward a resource (in src/main/webapp) as a response.
		 * The getRequestDispatcher() method of ServletRequest interface returns
		 * the object of RequestDispatcher. The RequestDispatcher interface
		 * provides the facility of dispatching the request to another resource
		 * it may be html, servlet or jsp. It Forwards a request from a servlet
		 * to another resource (servlet, JSP file, or HTML file) on the server.
		 * partials/login.html is the url-pattern of the partial html file that
		 * will ultimately be seen within the div tag with the id of view in the
		 * index.html page. Forwards both request and response objects.
		 */
		/*req.getRequestDispatcher("partials/login.html").forward(req, resp);
	}

}
*/