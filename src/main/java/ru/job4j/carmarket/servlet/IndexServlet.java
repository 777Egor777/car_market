package ru.job4j.carmarket.servlet;

import ru.job4j.carmarket.model.Advertisement;
import ru.job4j.carmarket.model.User;
import ru.job4j.carmarket.store.HibernateAdvertisementStore;

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
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("ads", HibernateAdvertisementStore.instOf().getAll(Advertisement.class));
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int adId = Integer.parseInt(req.getParameter("ad_id"));
        Advertisement ad = HibernateAdvertisementStore.instOf().getAtById(adId);
        ad.setStatus(false);
        HibernateAdvertisementStore.instOf().update(ad);
        resp.sendRedirect("index.do");
    }
}
