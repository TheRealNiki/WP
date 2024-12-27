package mk.ukim.finki.wp.lab1A.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab1A.model.EventBooking;
import mk.ukim.finki.wp.lab1A.model.User;
import mk.ukim.finki.wp.lab1A.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "EventBookingServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine) {
        this.eventBookingService = eventBookingService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        EventBooking booking = eventBookingService.getBookingByUserName(((User) req.getSession().getAttribute("user")).getUsername()).orElseThrow(ServletException::new);
        context.setVariable("attendeeName",booking.getAttendeeName());
        context.setVariable("clientIp",booking.getAttendeeAddress());
        context.setVariable("eventName",booking.getEventName());
        context.setVariable("numTickets",booking.getNumberOfTickets());

        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedEvent = req.getParameter("selectedEvent");
        String numTickets = req.getParameter("numTickets");
        eventBookingService.placeBooking(selectedEvent,((User)req.getSession().getAttribute("user")).getUsername(),req.getLocalAddr(), Integer.parseInt(numTickets));

        resp.sendRedirect("/eventBooking");
    }
}
