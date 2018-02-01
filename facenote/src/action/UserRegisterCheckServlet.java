package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersDao;

@WebServlet("/UserRegisterCheckServlet")
public class UserRegisterCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserRegisterCheckServlet 들어옴");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userEmail = request.getParameter("userEmail");
		
		int result = UsersDao.getInstance().registerCheck(userEmail);
		if(result == 1) {
			HttpSession session = request.getSession();
			String idCheck = "idCheckOn";
			session.setAttribute("idCheck", idCheck);
			response.getWriter().write(UsersDao.getInstance().registerCheck(userEmail) + "");
			return;
		}
		else {
			return;
		}
	}

}
