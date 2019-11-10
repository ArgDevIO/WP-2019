package mk.finki.ukim.mk.lab.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/selectPizzaSize", "/pizzaOrder"})
public class WarningFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        /**
         **************** BONUS TASK (+5pts) ******************
         * 1. Don't let the user continue to Pizza Size Selection
         *    if no pizza is selected when pressing SUBMIT!
         *    In this case show an error message "Please, select a pizza!"
         *    and send the user back to select a pizza!
         *
         * 2. Don't let the user continue to PizzaOrder
         *    if the selected pizza is "Margherita" when pressing SUBMIT on PizzaSizeSelection page!
         *    In this case show an error message "Margherita is out of stock!"
         *    and send the user back to select another pizza!
         */
        String path = httpRequest.getServletPath();
        String selectedPizza = (String) httpRequest.getSession().getAttribute("selectedPizza");

        switch (path) {
            case "/selectPizzaSize":
                if (selectedPizza == null || selectedPizza.isEmpty()) {
                    httpRequest.getSession().setAttribute("warning", "Please, select a pizza!");
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
