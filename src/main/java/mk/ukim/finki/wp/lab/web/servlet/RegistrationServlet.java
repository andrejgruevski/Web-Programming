
package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.service.RegistrationService;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private RegistrationService registrationService;
    private SpringTemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {

        WebApplicationContext context =
                WebApplicationContextUtils
                        .getRequiredWebApplicationContext(
                                getServletContext());

        this.registrationService =
                context.getBean(RegistrationService.class);

        this.templateEngine =
                context.getBean(SpringTemplateEngine.class);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String workshopTitle = req.getParameter("workshopTitle");
        String participantName = req.getParameter("participantName");
        String participantEmail = req.getParameter("participantEmail");

        try {
            int numberOfSeats =
                    Integer.parseInt(req.getParameter("numSeats"));

            Registration registration = registrationService.register(
                    workshopTitle,
                    participantName,
                    participantEmail,
                    numberOfSeats
            );

            Context context = new Context();

            context.setVariable("registration", registration);
            context.setVariable("clientIp", req.getRemoteAddr());
            context.setVariable("userAgent", req.getHeader("User-Agent"));

            resp.setContentType("text/html;charset=UTF-8");

            templateEngine.process(
                    "registrationConfirmation",
                    context,
                    resp.getWriter()
            );

        } catch (IllegalArgumentException e) {

            String errorMessage = URLEncoder.encode(
                    e.getMessage(),
                    StandardCharsets.UTF_8
            );

            resp.sendRedirect("/?errorMessage=" + errorMessage);
        }
    }
}
