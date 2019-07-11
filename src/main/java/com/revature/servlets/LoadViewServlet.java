package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.view")//any request that ends in .view comes here
public class LoadViewServlet extends HttpServlet{

	/*
	 * This servlet will follow the Front Controller
	 * design pattern (one servlet handling all requests
	 * and dispatching requests to appropriate 
	 * helper methods and/or classes) * to an extent 
	 * we will not send _every_ request here, only those
	 * with the suffix .view
	 * 
	 * We will name our partial html pages appropriately
	 * so that we will, for example, forward login.html
	 * along as a response to a request addressed to 
	 * login.view
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		System.out.println(req.getRequestURI());/*
		* When I type http://localhost:8081/myBankApp/login.view in browser,
		* the req.getRequestURI() is /myBankApp/login.view it contains the
		* context root(/myBankApp), the servlet path(/login.view), and path info.
		* The context root is used to select your particular application out of
		* many other applications that may be running in the server. The servlet
		* path is used to select a particular servlet out of many other servlets
		* that may be bundled in your application's WAR. There is no path info for
		* my URI, but it is interpreted by your servlet's logic (e.g. it may point
		* to some resource controlled by your servlet).
		*/
		String[] path = req.getRequestURI().split("/");/*
		* String indicates what type the array is, i.e. stores an array of
		* objects of type String. path is the name of the array. req.getRequestURI()
		* is a string. The delimeter is /. 
		*/
		System.out.println(path[0]+"=0 "+path[1]+ "=1 "+path[2]+ "=2");
		String resource = path[2].substring(0, path[2].length() - 5);/*
		* The array contains {, myBankApp, login.view}. We want the 2nd index of
		* path, which is login.view. Now we want the substring of the 2nd index of
		* path from the inclusive start index of 0 to the exclusive end index of
		* the length of the string - 5. For example, the length of login.view is
		* 10. Since 10-5 is 5, the end index is 5, which is exclusive. So, resource
		* includes indices 0-4 of login.view, where 0 points to l and 4 points to n.
		*/
		System.out.println("LOADING " + resource + ".html");
				
		String resourcePath = "partials/" + resource  + ".html";
		req.getRequestDispatcher(resourcePath).forward(req, resp);
	}

}
