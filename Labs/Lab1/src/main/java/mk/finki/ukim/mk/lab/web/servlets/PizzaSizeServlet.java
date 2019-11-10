package mk.finki.ukim.mk.lab.web.servlets;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/selectPizzaSize")
public class PizzaSizeServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public PizzaSizeServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("[WP-Log] {PizzaSizeServlet :: doGet()}");

        String selectedPizza = (String) req.getSession().getAttribute("selectedPizza");

        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("selectedPizza", selectedPizza);

        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());
    }
}
