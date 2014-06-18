package pt.adrz.gymlogger.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GymController
 * http://www.java-only.com/LoadTutorial.javaonly?id=13
 */
//@WebServlet("/GymController")
public class GymController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GymController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		testMethod01(request, response);
		//testMethod02(request, response);
		
	}
	
	protected void testMethod01(HttpServletRequest request, HttpServletResponse response) {
		// gather parameter specific information
		// RequestHelper helper = new RequestHelper(request);
		// Command cmdHelper = helper.getCommand();
		// page = cmdHelper.execute(request,response);
		
		try {
			String url = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/") + 1, request.getRequestURL().length());
			String controller = url.substring( 0 , url.indexOf("."));
			String action = request.getParameter("action");
			String view = "jsp/list.jsp";
			this.dispatch(request, response, view);
		}
		catch(StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// Action action = ActionFactory.getAction(request);
	}

	protected void testMethod02(HttpServletRequest request, HttpServletResponse response) {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
