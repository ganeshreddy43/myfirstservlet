package com.neoteric;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FirstServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println(" <h1> My first servlet demo<h1>");
    }
}
