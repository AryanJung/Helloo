import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {

	private static final String query = "delete record where id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        int id=Integer.parseInt(req.getParameter("id"));
        int Phone =Integer.parseInt(req.getParameter("Phone"));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javapro", "root", "");
                 PreparedStatement ps = con.prepareStatement(query);) {
            	ps.setInt(1, id);
            	int count = ps.executeUpdate();
                if (count == 1) {
                    pw.println("<h2>Record is Deleted Successfully</h2>");
                } else {
                    pw.println("<h2>Record is not Deleted Successfully</h2>");
                }
        } } catch (SQLException e) {
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }
        pw.println("<a href='main.html'> Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList'> LIST</a>");
        }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
     
    }
}
