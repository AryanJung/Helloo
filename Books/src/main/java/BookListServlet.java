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
@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {

	private static final String query = "SELECT Name, Password, Email, Phone FROM record";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javapro", "root", "");
                 PreparedStatement ps = con.prepareStatement(query);) {
            	ResultSet rs=ps.executeQuery();
            	pw.println("<table border='1'>");
            	pw.println("<tr>");
            	pw.println("<th>Name</th>");
            	pw.println("<th>Password</th>");
            	pw.println("<th>Email</th>");
            	pw.println("<th>Phone</th>");
            	pw.println("<th>EDIT</th>");
            	pw.println("<th>DELETE</th>");
            	pw.println("</tr>");
            	while(rs.next()) {
            		pw.println("<tr>");
                	pw.println("<td>"+rs.getString(1)+"</td>");
                	pw.println("<td>"+rs.getInt(2)+"</td>");
                	pw.println("<td>"+rs.getString(3)+"</td>");
                	pw.println("<td>"+rs.getInt(4)+"</td>");
                	pw.println("<td><a href='editScreen?id=="+rs.getString(1)+"'>Edit</a></td>");
                	pw.println("<td><a href='deleteurl?id=="+rs.getString(1)+"'>Delete</a></td>");
                	pw.println("<td><a></a></td>");
                	pw.println("</tr>");
            	}
            	pw.println("</table>");
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