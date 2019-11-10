package mk.finki.ukim.mk.lab.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/selectPizzaSize", "/pizzaOrder"})
public class WarningFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String path = httpRequest.getServletPath();
        String selectedPizza = (String) httpRequest.getSession().getAttribute("selectedPizza");

        switch (path) {
            case "/selectPizzaSize":
                if (selectedPizza == null || selectedPizza.isEmpty()) {
                    httpRequest.getSession().setAttribute("warning", "Please select a pizza!");
                    httpResp.sendRedirect("/");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                break;
            case "/pizzaOrder":
                if (selectedPizza.equals("Margherita")) {
                    httpRequest.getSession().setAttribute("warning", "Margherita out of stock!");
                    httpResp.sendRedirect("/");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                break;
            default:
                filterChain.doFilter(servletRequest, servletResponse);
                break;
        }
    }

    @Override
    public void destroy() {

    }
}
