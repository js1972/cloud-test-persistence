package au.com.jaylin.test.db;

import java.io.IOException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.security.auth.login.LoginContextFactory;


/**
 * Log the user out of SAPHCP.
 * 
 * There is an issue in that the logout sequence is different for SAPHCP and the local
 * runtime. Also - this will not work on a different JEE6 container due to the :
 * com.sap.security.auth.login.LoginContextFactory requirement.
 * See the cloud-test-rest sample application to see how to do this with reflection so that
 * it will work on a local Tomcat instance as well. 
 * 
 * SAPHCP Help: https://help.hana.ondemand.com/help/frameset.htm?2eebf764c3e34900b92a6ba3e4654ccd.html
 * 
 */
@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 6697231061518263549L;

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Call logout if the user is logged in
		LoginContext loginContext = null;
		
		if (request.getRemoteUser() != null) {
			try {
				loginContext = LoginContextFactory.createLoginContext();
				loginContext.logout();
			} catch (LoginException le) {
				response.getWriter().println("Logout failed. Reason: " + le.getMessage());
			}
			
			// this section is only called on local saphcp runtime! I think its because
			// the SAP Id Provider does a redirect on the logout() method above; so we
			// re-enter this servlet with no user and therefore it execute the "else"
			// logic below.
			
			String url = request.getRequestURL().toString();
			if (url.indexOf("localhost") > -1) {
				//response.getWriter().println("<You have successfully logged out...");
				response.sendRedirect("logout.html");
			}
		} else {
			//response.getWriter().println("You are already logged out.");
			response.sendRedirect("logout.html");
		}
	}
}
