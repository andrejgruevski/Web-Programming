
package mk.finki.ukim.wp.aud.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private List<Category> categories;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Category {
        private String name;
        private String description;
    }

    @Override
    public void init() {
        categories = new ArrayList<>();

        addCategory("Movies", "Movies category");
        addCategory("Books", "Books category");

        getServletContext().setAttribute("categories", categories);
    }

    private void addCategory(String name, String description) {

        if (name == null || name.isBlank()
        || description == null || description.isBlank()){
            return;
        }
        name = name.trim();
        description = description.trim();

        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return;
            }
        }
        categories.add(new Category(name, description));
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        SpringTemplateEngine templateEngine =
                WebApplicationContextUtils
                        .getRequiredWebApplicationContext(getServletContext())
                        .getBean(SpringTemplateEngine.class);

        IWebExchange webExchange =
                JakartaServletWebApplication
                        .buildApplication(getServletContext())
                        .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("userAgent", req.getHeader("user-agent"));
        context.setVariable("categories", categories);

        resp.setContentType("text/html;charset=UTF-8");

        templateEngine.process(
                "categories", context, resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        addCategory(name, description);

        resp.sendRedirect(req.getContextPath() + "/servlet/category");
    }
}
