package com.artist.controller;

import com.artist.dao.ArtistDAO;
import com.artist.model.Event;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Artist/InsertEventServlet")
public class InsertEventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventId = request.getParameter("txtID");
        String artistName = request.getParameter("txtaname");
        String eventName = request.getParameter("txtname");
        String eventDate = request.getParameter("txtdate");
        String eventVenue = request.getParameter("txtvenue");
        String eventCapacity = request.getParameter("txtcap");
        String ticketPrice = request.getParameter("txtprice");

        Event event = new Event(eventId, artistName, eventName, eventDate, eventVenue, eventCapacity, ticketPrice);

        ArtistDAO eventDAO = new ArtistDAO();
        try {
            boolean result = eventDAO.insertEvent(event); // Assuming insertEvent returns the number of affected rows
            if (result == true) {
                // Set the inserted data as request attributes
                request.setAttribute("eventId", eventId);
                request.setAttribute("artistName", artistName);
                request.setAttribute("eventName", eventName);
                request.setAttribute("eventDate", eventDate);
                request.setAttribute("eventVenue", eventVenue);
                request.setAttribute("eventCapacity", eventCapacity);
                request.setAttribute("ticketPrice", ticketPrice);

                // Forward to insertsuccess.jsp
                request.setAttribute("message", "Event successfully added!");
                request.getRequestDispatcher("Insertsuccess.jsp").forward(request, response);
            } else {
                // Insertion failed
                request.setAttribute("error", "Failed to add event.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}