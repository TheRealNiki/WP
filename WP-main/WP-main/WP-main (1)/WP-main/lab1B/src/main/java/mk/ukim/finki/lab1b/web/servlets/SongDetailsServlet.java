//package mk.ukim.finki.lab1b.web.servlets;
//
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.lab1b.model.Song;
//import mk.ukim.finki.lab1b.service.SongService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "song-details-servlet",urlPatterns = "/songDetails")
//public class SongDetailsServlet extends HttpServlet {
//    private final SongService songService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public SongDetailsServlet(SongService songService, SpringTemplateEngine springTemplateEngine) {
//        this.songService = songService;
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
//
//        String trackId= req.getParameter("songId");
//        Song song = songService.findByTrackId(trackId).orElseThrow(RuntimeException::new);
//
//        context.setVariable("song",song);
//
//        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
//    }
//}
