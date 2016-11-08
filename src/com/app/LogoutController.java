package com.app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	@RequestMapping(value = "/Logout")
	public ModelAndView logoff(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession session = req.getSession(false);
		String name = (String) session.getAttribute("name");
		System.out.println(name);

		req.setAttribute("message", "Logged out Successfully " + name);

		session.setAttribute("name", null);

		session.invalidate();

		RequestDispatcher rd = req.getRequestDispatcher("/");
		rd.forward(req, res);

		return null;

	}

	@RequestMapping(value = "/Home")
	public ModelAndView callhome(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

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
				mv = new ModelAndView("Menu");
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
