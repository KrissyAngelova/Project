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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/SignInServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter()
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (emailValidate(email)) {
			for (User user : IUserDao.getDAO(IUserDao.DataSource.DB).getAllUsers()) {
				if (user.getEmail().equals(email) && user.getPass().equals(password)) {
					request.getSession().setAttribute("loggedUser", user);
					response.sendRedirect("index.html");
					return;
				}
			}
		}
		response.sendRedirect("login.html");
	}

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean emailValidate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
}
