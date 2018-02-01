package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersDao;
import model.UsersDto;

public class writeContentAction implements CommandAction{

		@Override
		public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session=request.getSession();
			String loginUserEmail = (String)session.getAttribute("loginUserEmail");
			UsersDao userDao = UsersDao.getInstance();
			UsersDto loginUser = userDao.getUser(loginUserEmail);				
			
			UsersDao UDA = UsersDao.getInstance();
			System.out.println("writeAction");
			
			UsersDto pageUser = new UsersDto();
			String pageEmail;

			pageEmail = request.getParameter("pageemail");			
			
			pageUser = UDA.getUser(pageEmail);
			
			request.setAttribute("loginUser", loginUser);
			
			request.setAttribute("pageUser", pageUser);
			
			return "/jsp/page/writeForm.jsp";
		}
}
