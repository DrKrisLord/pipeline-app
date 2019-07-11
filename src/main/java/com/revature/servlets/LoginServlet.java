package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.User;
import com.revature.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	/*
	 * USING LOG4J!
	 */
	final static Logger logger = Logger.getLogger(LoginServlet.class);
	UserService service = new UserService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		//retrieve user from req body 
		User user = mapper.readValue(req.getInputStream(), User.class);
		//pass in username and password to service layer, get user obj
		user = service.login(user.getUsername(), user.getPassword());
		//if obj == null, invalid credentials. send back null
		if(user == null) {
			resp.setStatus(204);
		}
		//if obj != null, add user to session, send back user data 
		else {
			HttpSession session = req.getSession();/*
			* When a user enters into a website (or an online application)
			* for the first time HttpSession is obtained via request.getSession(),
			* the user is given a unique ID to identify his session. This unique
			* ID can be stored into a cookie or in a request parameter.
			*/
			logger.info("Created HttpSession " + session.getId());
			
			session.setAttribute("loggedUser", user);/*
			* You can store the user information into the session object by
			* using setAttribute() method and later when needed this information
			* can be fetched from the session. This is how you store info in
			* session. TO get the value from session we use the getAttribute()
			* method of HttpSession interface.
			*/
			logger.info("Logged in user " + user);
			
			
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json");
			resp.setStatus(200);
			writer.write(mapper.writeValueAsString(user));
		}
	}

}
