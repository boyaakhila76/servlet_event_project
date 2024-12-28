package event_ms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/create")
public class CreateServlet extends HttpServlet{
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String loc = req.getParameter("loc");
		String date = req.getParameter("date");
		String guest = req.getParameter("guest");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "1234");
			PreparedStatement ps = con.prepareStatement("insert into event values(?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, title);
			ps.setString(3, loc);
			ps.setString(4, date);
			ps.setString(5, guest);

			int row = ps.executeUpdate();
			PrintWriter pw = res.getWriter();
			pw.write("<html><body><h1> Event Registered Successfully Done... </h1></body></html>");

			ps.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
