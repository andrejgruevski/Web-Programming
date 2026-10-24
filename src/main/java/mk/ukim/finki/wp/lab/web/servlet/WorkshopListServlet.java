
package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mk.ukim.finki.wp.lab.service.WorkshopService;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.WebApplicationContext;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import mk.ukim.finki.wp.lab.model.Workshop;
import java.util.List;

import java.io.IOException;

@WebServlet(urlPatterns = {"", "/workshops"})
public class WorkshopListServlet extends HttpServlet {

    private WorkshopService workshopService;
    private SpringTemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context =
                WebApplicationContextUtils
                        .getRequiredWebApplicationContext(
                                getServletContext());

        this.workshopService =
                context.getBean(WorkshopService.class);

        this.templateEngine =
                context.getBean(SpringTemplateEngine.class);
    }


    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String text = req.getParameter("text");
        String maxPriceParam = req.getParameter("maxPrice");

        if (text != null && text.isBlank()) {
            text = null;
        }

        Double maxPrice = null;

        if (maxPriceParam != null && !maxPriceParam.isBlank()) {
            try {
                maxPrice = Double.parseDouble(maxPriceParam);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid max price");
                return;
            }
        }

        List<Workshop> workshops;

        if (req.getParameter("text") != null || maxPriceParam != null) {
            workshops = workshopService.search(text, maxPrice);
        } else {
            workshops = workshopService.listAll();
        }

        Context context = new Context();

        context.setVariable("workshops", workshops);

        context.setVariable(
                "errorMessage",
                req.getParameter("errorMessage")
        );

        resp.setContentType("text/html;charset=UTF-8");

        templateEngine.process(
                "listWorkshops",
                context,
                resp.getWriter()
        );
    }

}
