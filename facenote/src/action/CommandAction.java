package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
	public String requestPro(HttpServletRequest req, 
						HttpServletResponse reps) throws Throwable;
}
