package com.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value = "/")
	public ModelAndView LoginPage(HttpServletRequest req, HttpServletResponse res) {

		String message = (String) req.getAttribute("message");

		if (message == null) {

			System.out.println("Home page entered with empty");

			ModelAndView mv = new ModelAndView("Login");

			System.out.println("Home Page redirected");

			return mv;

		}

		else {

			System.out.println("Home page entered with message");

			ModelAndView mv = new ModelAndView("Login");

			System.out.println("Home Page redirected");

			mv.addObject("message", message);

			return mv;

		}
	}

	@RequestMapping(value = "/LoginValidate", method = RequestMethod.POST)
	public ModelAndView Validatelogin(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		ModelAndView mv;
		try {

			PersistenceManager pm = PMF.get().getPersistenceManager();

			User e = pm.getObjectById(User.class, email);

			if (password.equals(e.getPassword())) {

				HttpSession session = req.getSession();

			try{	if (session != null) {

					mv = new ModelAndView("Menu");
					mv.addObject("name", e.getName());
					
					session.setAttribute("name", e.getName());
					session.setAttribute("email", e.getEmail());
				}

				else {

					System.out.println("Session expired");

					req.removeAttribute("message");

					req.setAttribute("message", "Session Expired");

					res.sendRedirect("/");

					mv = null;
				}
			}catch(NullPointerException ex){
				
				System.out.println("Session expired");

				req.removeAttribute("message");

				req.setAttribute("message", "Session Expired");

				res.sendRedirect("/");

				mv = null;
				
				
			}
			} else {

				System.out.println("Invalid credentials");

				req.removeAttribute("message");
				req.setAttribute("message", "Invalid credentials");

				RequestDispatcher rd = req.getRequestDispatcher("/");

				rd.forward(req, res);

				mv = null;
			}

		} catch (JDOObjectNotFoundException e) {

			System.out.println("Not an user");

			req.removeAttribute("message");
			req.setAttribute("message", "Invalid user not recognised");

			RequestDispatcher rd = req.getRequestDispatcher("/");

			rd.forward(req, res);

			mv = null;

		}

		return mv;

	}

}
