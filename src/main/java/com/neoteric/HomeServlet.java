package com.neoteric;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if ("forward".equals(action)) {
            RequestDispatcher rd = request.getRequestDispatcher("/forward");
            rd.forward(request, response);
        } else if ("redirect".equals(action)) {
            response.sendRedirect("https://www.google.com");
        } else if ("include".equals(action)) {
            out.println("<h2>Home Page</h2>");
            RequestDispatcher rd = request.getRequestDispatcher("/included");
            rd.include(request, response);
        } else {
            out.println("""
                <html><body>
                <form method='get' action='home'>
                            <input type='submit' name='action' value='forward'/>
                            <input type='submit' formaction='redirect' value='redirect'/>
                            <input type='submit' name='action' value='include'/>
                </form>
                </body></html>
            """);
        }
    }
}
