package com.app;

import java.io.IOException;

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
public class UpdateNameController {

	@RequestMapping(value = "/Name")
	public ModelAndView NamePage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
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

				mv = new ModelAndView("UpdateName");

			}
		} catch (NullPointerException ex) {
			req.setAttribute("message", "Please login to continue");

			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, res);
			mv = null;

		}
		return mv;
	}

	@RequestMapping(value = "/NameChange", method = RequestMethod.POST)
	public ModelAndView nameupdate(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String name = req.getParameter("name");
				ModelAndView mv;
		HttpSession session = req.getSession(false);
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

				PersistenceManager pm = PMF.get().getPersistenceManager();
				String email = (String) session.getAttribute("email");

				User e = pm.getObjectById(User.class, email);
				e.setName(name);
				session.setAttribute("name", name);
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
