import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ShowEntries extends HttpServlet {

@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// Set the MIME type for the response message
response.setContentType("text/html");

// Get a output writer to write the response message into the network socket
PrintWriter out = response.getWriter();
Connection conn = null;
Statement stmt = null;
out.println("<html><body>");
try {
// Step 1: Create a database "Connection" object
conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimeZone=UTC", "root", "123456");
// Step 2: Create a "Statement" object inside the "Connection"
stmt = conn.createStatement();

// Step 3: Execute a SQL SELECT query
String sqlStr = "SELECT * FROM address ";
ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server// Step 4: Process the query result
// Print an HTML page as output of query
out.println("<h2>Table of Entries</h2>");
out.println("<table border=1 width=80% height=50%>");
out.println("<tr><th>firstName</th><th>middleName</th><th>lastName</th><th>phoneNumber</th><th>workNumber</th><th>mail</th><tr>");


int count = 0;

while(rset.next()) {
// Print a paragraph <p>...</p> for each row
String a = rset.getString("firstName");
String b = rset.getString("middleName");
String c = rset.getString("lastName");
int d = rset.getInt("mobileNumber");
int e = rset.getInt("workNumber");
String f = rset.getString("email");
out.println("<tr><td>" + a + "</td><td>" + b + "</td><td>" + c + "</td><td>" + d + "</td><td>"+ e + "</td><td>" + f + "</td></tr>");
}

out.println("/table>");
out.println("</html><body>");
conn.close();
}

catch (SQLException ex) {
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
