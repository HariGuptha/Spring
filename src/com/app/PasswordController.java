package com.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PasswordController {

	@RequestMapping(value = "/Password")
	public ModelAndView passwordpage(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		ModelAndView mv;

		try {
			if (session == null) {

				req.setAttribute("message", "Please login to continue");

				RequestDispatcher rd = req.getRequestDispatcher("/");
				rd.forward(req, res);
				mv = null;

			} else if (session.getAttribute("name") == null) {
				req.setAttribute("message", "Please login to continue");

				RequestDispatcher rd = req.getRequestDispatcher("/");
				rd.forward(req, res);
				mv = null;

			}

			else {
				mv = new ModelAndView("Password");
			}
		} catch (NullPointerException ex) {
			req.setAttribute("message", "Please login to continue");

			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, res);
			mv = null;
		}
		return mv;
	}

	@RequestMapping(value = "/PasswordUpdate", method = RequestMethod.POST)
	public ModelAndView PasswordUpdate(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		PrintWriter out = res.getWriter();
		String oldpass = req.getParameter("pass");
		String newpass = req.getParameter("passnew");
		ModelAndView mv;
		
		try {
			if (session == null) {
				req.setAttribute("message", "Please login to continue");

				RequestDispatcher rd = req.getRequestDispatcher("/");
				rd.forward(req, res);
				mv = null;

			}

			else if (session.getAttribute("name") == null) {
				req.setAttribute("message", "Please login to continue");

				RequestDispatcher rd = req.getRequestDispatcher("/");
				rd.forward(req, res);
				mv = null;

			}

			else {
				String email = (String) session.getAttribute("email");
				if (newpass.length() > 5) {

					if (!newpass.equals(oldpass)) {
						PersistenceManager pm = PMF.get().getPersistenceManager();
						User e = pm.getObjectById(User.class, email);

						if (oldpass.equals(e.getPassword())) {
							e.setPassword(newpass);
							out.println("<p>Password Updated Successfully </p>");
							RequestDispatcher rd = req.getRequestDispatcher("/Home");
							rd.forward(req, res);
							mv = null;

						}

						else {
							out.println("<p>Enter correct password</p>");

							mv = new ModelAndView("Password");

						}

					} else {
						out.println("<p>Use another new password resembles old  password</p>");

						mv = new ModelAndView("Password");

					}
				}

				else {
					out.println("<p>Password should contain 6 characters</p>");

					mv = new ModelAndView("Password");
				}

			}

		} catch (NullPointerException ex) {
			req.setAttribute("message", "Please login to continue");

			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, res);
			mv = null;
		}

		return mv;
	}

}
