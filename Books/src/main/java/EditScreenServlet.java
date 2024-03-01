import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {

	private static final String query = "SELECT Name, Password, Email, Phone FROM record where id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        int id=Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javapro", "root", "");
                 PreparedStatement ps = con.prepareStatement(query);) {
            	
            	ps.setInt(1, id);
            	ResultSet rs=ps.executeQuery();
            	rs.next();
            	pw.println("<form action='editurl?id=" + id + "' method='post'>");
                pw.println("<table align='center'>");
                pw.println("<tr>");
                pw.println("<td>Name</td>");
                pw.println("<td><input type='text' name='Name' value='" + rs.getString(1) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td>Password</td>");
                pw.println("<td><input type='password' name='Password' value='" + rs.getString(2) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td>Email</td>");
                pw.println("<td><input type='email' name='Email' value='" + rs.getString(3) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td>Phone</td>");
                pw.println("<td><input type='number' name='Phone' value='" + rs.getInt(3) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td><input type='submit' value='Edit'></td>");
                pw.println("<td><input type='reset' value='cancel'></td>");
                pw.println("</tr>");
                pw.println("</table>");
                pw.println("</form>");
        } } catch (SQLException e) {
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }
        pw.println("<a href='main.html'> Home</a>");
        }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
     
    }
}
