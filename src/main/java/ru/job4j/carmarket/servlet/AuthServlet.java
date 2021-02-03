package ru.job4j.carmarket.servlet;

import ru.job4j.carmarket.model.User;
import ru.job4j.carmarket.store.HibernateUserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 31.01.2021
 */
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = HibernateUserStore.instOf().get(login);

        if (user == null) {
            resp.sendRedirect("auth.jsp?errMsg=\"No such login\"");
        } else if (!user.getPassword().equals(password)) {
            resp.sendRedirect("auth.jsp?errMsg=\"Incorrect password\"");
        } else {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("index.do");
        }
    }
}