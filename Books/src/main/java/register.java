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

@WebServlet("/register")
public class register extends HttpServlet {
    private static final String query = "INSERT INTO record(Name, Password, Email, Phone) VALUES (?, ?, ?, ?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        String Name = req.getParameter("Name");
        String Password = req.getParameter("Password");
        String Email = req.getParameter("Email");
        Float Phone = Float.parseFloat(req.getParameter("Phone"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javapro", "root", "");
                 PreparedStatement ps = con.prepareStatement(query);) {
                ps.setString(1, Name);
                ps.setString(2, Password);
                ps.setString(3, Email); 
                ps.setFloat(4, Phone);
                int count = ps.executeUpdate();
                if (count == 1) {
                    pw.println("<h2> RECORD IS INSERTED SUCCESSFULLY</h2>");
                } else {
                    pw.println("<h2> RECORD NOT INSERTED</h2>");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
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
