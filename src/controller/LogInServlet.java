package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.dao.IUserDao;
import model.dao.IUserDao.DataSource;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		if(emailValidate(username)){
			try{
				//ne raboti
				for(User u : IUserDao.getDAO(DataSource.DB).getAllUsers()){	
									
					if(u.getEmail().equals(username) && u.getPass().equals(password)){
						request.getSession().setAttribute("loggedUser", u);
						//prashtame go na nachalna stranica
						response.sendRedirect("index.html");
					}
				}
				
			}catch(SQLException e){
				response.sendRedirect("login.html");
			}
		}else{
		//response.sendRedirect(/*loginFailed*/);
		}
	}
	
	//e-mail validation
		public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

			public static boolean emailValidate(String emailStr) {
			        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
			        return matcher.find();
			}

}
