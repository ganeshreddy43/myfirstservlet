package com.neoteric;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Page layout for the table
        out.println("<html><body>");
        out.println("<h2>Welcome to Subject Data</h2>");
        out.println("<table border='1'><tr><th>Subject Code</th><th>Subject Name</th><th>Semester</th><th>Internal</th><th>External</th><th>Total</th><th>Grade</th><th>Result</th><th>Reg/Sup</th><th>Exam Month/Year</th></tr>");

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection details (replace with your actual DB credentials)
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/update_studentdata", "root", "Ganesh_43");

            // SQL query to fetch subject data from subject_data table
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subject_data");

            // Iterate through result set and display data in table rows
            while (rs.next()) {
                String subjectCode = rs.getString("subject_code");
                String subjectName = rs.getString("subject_name");
                String semester = rs.getString("semester");
                int internal = rs.getInt("internal");
                int external = rs.getInt("external");
                int total = rs.getInt("total");
                String grade = rs.getString("grade");
                String result = rs.getString("result");
                String regOrSup = rs.getString("reg_or_sup");
                String examMonthYear = rs.getString("exam_month_year");

                // Display each subject's data in a table row
                out.println("<tr><td>" + subjectCode + "</td><td>" + subjectName + "</td><td>" + semester + "</td><td>"
                        + internal + "</td><td>" + external + "</td><td>" + total + "</td><td>" + grade + "</td><td>"
                        + result + "</td><td>" + regOrSup + "</td><td>" + examMonthYear + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body></html>");
            conn.close(); // Close the connection
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
            e.printStackTrace(out); // Print exception details
        }
    }
}
