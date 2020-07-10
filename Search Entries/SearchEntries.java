// Saved as "ebookshop\WEB-INF\src\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class SearchEntries extends HttpServlet {
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
try {
// Step 1: Create a database "Connection" object
conn = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/addressbook?serverTimeZone=UTC", "root", "123456");
// Step 2: Create a "Statement" object inside the "Connection"
stmt = conn.createStatement();
// Step 3: Execute a SQL SELECT query
String sqlStr = "SELECT * FROM address WHERE lastName = "
+ "'" + request.getParameter("lastName") + "'";

// Print an HTML page as output of query
out.println("<html><head><title>Query Results</title></head><body>");
out.println("<h2>Here is address.</h2>");
out.println("<p>You address is: " + sqlStr + "</p>"); // Echo for debugging
ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server// Step 4: Process the query result
int count = 0;


while(rset.next()) {
// Print a paragraph <p>...</p> for each row
out.println("<p>" + rset.getString("firstName"));
out.println("<p>" + rset.getString("middleName"));
out.println("<p>"+ rset.getString("lastName"));
out.println("<p>" + rset.getInt("mobileNumber"));
out.println("<p>" + rset.getInt("workNumber"));
out.println("<p>" + rset.getString("email"));
++count;
}

out.println("<p>==== " + count + " records found ====</p>");
out.println("</body></html>");
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
