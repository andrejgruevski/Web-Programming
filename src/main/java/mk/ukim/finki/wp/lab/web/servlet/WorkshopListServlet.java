
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

        Context context = new Context();

        context.setVariable(
                "workshops",
                workshopService.listAll()
        );

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
