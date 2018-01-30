package controller;


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import action.CommandAction;

public class ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("propertyConfig");
		
		Properties pr = new Properties();
		FileInputStream f = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			f = new FileInputStream(new File(path, props));
			
			pr.load(f);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(f != null) {
					f.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			
			try {
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				
				commandMap.put(command, commandInstance);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		try {
			requestPro(req, reps);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
		try {
			requestPro(req, reps);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void requestPro(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException, Throwable {
		String view = null;
		CommandAction com = null;
		
		try {
			String command = req.getRequestURI();
			if(command.indexOf(req.getContextPath()) == 0) {
				command = command.substring(req.getContextPath().length());
			}
			com = (CommandAction)commandMap.get(command);
			
			System.out.println(com);
			System.out.println(command);
			
			view = com.requestPro(req, reps);
		}catch(Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		
		dispatcher.forward(req, reps);
		
	}
}