package mk.finki.ukim.wp.aud.web.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

import java.io.IOException;

@WebServlet(name = "DeleteCategoryServlet", urlPatterns = "/servlet/category/delete")
public class DeleteCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");

        Object stored = getServletContext().getAttribute("categories");

        if (name != null && stored instanceof List<?> categories){
            String categoryName = name.trim();

            categories.removeIf(item ->
                    item instanceof CategoryServlet.Category category && category.getName().equalsIgnoreCase(categoryName)
            );
        }

        resp.sendRedirect(req.getContextPath() + "/servlet/category");
    }
}
