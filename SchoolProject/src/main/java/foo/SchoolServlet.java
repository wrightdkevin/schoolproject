package foo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

		List<Student> studentList = new ArrayList<Student>();

		java.io.PrintWriter pw = response.getWriter();
		//pw.println("servlet is running ok");
		String studentId = request.getParameter("studentid");
		//pw.println("I have been passed student id:" + studentId);

		// Now get the relation
		String relation = request.getParameter("relation");
		//pw.println("relation is:" + relation);

		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/test?"
							+ "user=root&password=root");
			if (conn == null) {
				pw.println("Did not get a connection... you need to investigate");
				return;
			}

			//pw.println("success you got a db connection");
			String sqlQuery = "select studentid, name from students where studentid";

			if (relation.equals("Equals"))
				sqlQuery += " = ?";
			else if (relation.equals("Greater Than"))
				sqlQuery += " > ?";
			else
				sqlQuery += " < ?";

			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, studentId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String stId = rs.getString(1);
				String name = rs.getString(2);
				// pw.println("Student id:" + stId + " name:" + name);
				studentList.add( new Student(Integer.parseInt(stId), name));
				
			}
			
			// Bind the List of students to the request.
			request.setAttribute("studentList", studentList);
			RequestDispatcher rd = request.getRequestDispatcher("results.jsp");
			rd.forward(request, response);
			

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
