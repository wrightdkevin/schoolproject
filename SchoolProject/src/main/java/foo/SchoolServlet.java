package foo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SchoolServlet
 */
public class SchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SchoolServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		java.io.PrintWriter pw = response.getWriter();
		pw.println("servlet is running ok");
		String studentId = request.getParameter("studentid");
		pw.println("I have been passed student id:" + studentId);
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/test?"
							+ "user=root&password=root");
			if( conn == null)
				pw.println("Did not get a connection... you need to investigate");
			else
				pw.println("success you got a db connection");
			

		} catch (ClassNotFoundException cnfd) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
