import java.io.IOException;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/testdb", "postgres", "lokesh@09");
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username, password) VALUES(?, ?)");
            ps.setString(1, req.getParameter("username"));
            ps.setString(2, req.getParameter("password"));
            ps.executeUpdate();
            con.close();
            req.setAttribute("user", req.getParameter("username"));
            req.getRequestDispatcher("welcome.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}