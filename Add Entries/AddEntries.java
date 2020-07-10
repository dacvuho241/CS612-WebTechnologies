// Saved as "ebookshop\WEB-INF\src\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AddEntries extends HttpServlet {
// JDK 6 and above only
// The doGet() runs once per HTTP GET request to this servlet.
@Override
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
// Set the MIME type for the response message
response.setContentType("text/html");
// Get a output writer to write the response message into the network socket
PrintWriter out = response.getWriter();
Connection conn = null;
Statement stmt = null;

String fName = request.getParameter("firstName");
String mName = request.getParameter("middleName");
String lName = request.getParameter("lastName");
int pNumber = Integer.parseInt(request.getParameter("phoneNumber"));
int wNumber = Integer.parseInt(request.getParameter("workNumber"));
String email = request.getParameter("email");

try {
// Step 1: Create a database "Connection" object
conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimeZone=UTC", "root", "123456");
// Step 2: Create a "Statement" object inside the "Connection"
stmt = conn.createStatement();
// Step 3: Execute a SQL SELECT query
//String sqlStr = "SELECT * FROM address WHERE lastName = "
//+ "'" + request.getParameter("lastName") + "'";
String sql = "insert into address values (?,?,?,?,?,?)";
PreparedStatement pst = conn.prepareStatement(sql);
pst.setString(1,fName);
pst.setString(2,mName);
pst.setString(3,lName);
pst.setInt(4,pNumber);
pst.setInt(5,wNumber);
pst.setString(6,email);
int numRowsChanged = pst.executeUpdate();
out.println("<p>"+ "Hi"+ fName);
out.println("<p>"+"Congratulate!!! Your entry has been added.");
out.println("<p>"+"- Your name: " + fName + lName);
out.println("<p>"+"- Your phone number: " + pNumber);
out.println("<p>"+"- Your work number: " + wNumber);
out.println("<p>"+"- Your email: " + email);
} catch (SQLException ex) {
out.println(ex.getMessage());
} finally {
out.close();
try {
// Step 5: Close the Statement and Connection
if (stmt != null) stmt.close();
if (conn != null) conn.close();
} catch (SQLException ex) {
out.println(ex.getMessage());
}
}
}
}
