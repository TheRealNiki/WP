package mk.ukim.finki.wp.lab1A.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab1A.model.User;


import java.io.IOException;


@WebFilter(filterName = "auth-filter", urlPatterns = "/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        initParams = @WebInitParam(name = "ignore-path", value = "/login"))
public class LoginFilter implements Filter {

    private String ignorePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignorePath = filterConfig.getInitParameter("ignore-path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Check if the user is logged in
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute("user");

        String path = ((HttpServletRequest) servletRequest).getServletPath();

        if (ignorePath.startsWith(path) || user != null) {
            // If the user is logged in or the requested path is the login page, continue with the request
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // If the user is not logged in and the requested path is not the login page, redirect to the login page
            resp.sendRedirect("/login");
        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
