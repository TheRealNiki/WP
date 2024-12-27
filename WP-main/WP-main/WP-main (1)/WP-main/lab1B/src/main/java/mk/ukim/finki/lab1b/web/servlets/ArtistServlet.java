//package mk.ukim.finki.lab1b.web.servlets;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.lab1b.model.Artist;
//import mk.ukim.finki.lab1b.model.Song;
//import mk.ukim.finki.lab1b.service.ArtistService;
//import mk.ukim.finki.lab1b.service.SongService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "artist-servlet",urlPatterns = "/artist")
//public class ArtistServlet extends HttpServlet {
//    private final ArtistService artistService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public ArtistServlet(ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
//        this.artistService = artistService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("trackId",req.getParameter("songId"));
//        List<Artist> artists = artistService.listArtists();
//        context.setVariable("artists",artists);
//        springTemplateEngine.process("artistsList.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String trackId = req.getParameter("songId");
//        resp.sendRedirect("/artist?songId="+trackId);
//    }
//}
