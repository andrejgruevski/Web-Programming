
package mk.finki.ukim.wp.aud.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "HelloWorldServlet", urlPatterns = {"", "/hello"})
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        if (name == null) name = "X";
        if (surname == null) surname = "Y";

        SpringTemplateEngine springTemplateEngine =
                WebApplicationContextUtils
                        .getRequiredWebApplicationContext(getServletContext())
                        .getBean(SpringTemplateEngine.class);

        IWebExchange webExchange =
                JakartaServletWebApplication
                        .buildApplication(getServletContext())
                        .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("name", name);
        context.setVariable("surname", surname);

        resp.setContentType("text/html;charset=UTF-8");

        springTemplateEngine.process(
                "hello", context, resp.getWriter()
        );
    }
}
