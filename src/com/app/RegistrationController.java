package com.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

	@RequestMapping(value = "/RegisterPage", method = RequestMethod.GET)
	public ModelAndView RegisterPageCall() {

		System.out.println("Calling register page");

		ModelAndView mv = new ModelAndView("Register");

		System.out.println("Register called");

		return mv;

	}

	@RequestMapping(value = "/Registration", method = RequestMethod.POST)
	public ModelAndView Register(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		System.out.println("Registration page");

		ModelAndView mv;
		try {

			if (password.length() > 5) {

				User u = pm.getObjectById(User.class, email);
				out.println("<p>Email already exists</p>");
				System.out.println("Exists");

				mv = new ModelAndView("Register");

			}

			else {
				out.println("<p>Password should contain 6 characters</p>");
				System.out.println("Wrong pattern");

				mv = new ModelAndView("Register");
			}

		} catch (JDOObjectNotFoundException e) {

			User user = new User(name, email, password);

			pm.makePersistent(user);

			System.out.println("Registered");
			
			req.getSession().setAttribute("name", name);
			
			req.getSession().setAttribute("email", email);
		
			

			req.setAttribute("message", "User registered successfully");

			RequestDispatcher rd = req.getRequestDispatcher("/Home");

			rd.forward(req, res);

			mv = null;
		}

		return mv;
	}

}
