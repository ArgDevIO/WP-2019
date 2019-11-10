package mk.finki.ukim.mk.lab.web.servlets;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/pizzaOrder")
public class PizzaOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public PizzaOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("[WP-Log] {PizzaOrderServlet :: doPost()}");

        String selectedPizza = (String) req.getSession().getAttribute("selectedPizza");
        String selectedPizzaSize = req.getParameter("pizza_size");

        req.getSession().setAttribute("selectedPizzaSize", selectedPizzaSize);

        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("selectedPizza", selectedPizza);
        webContext.setVariable("selectedPizzaSize", selectedPizzaSize);

        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("deliveryInfo.html", webContext, resp.getWriter());
    }
}
