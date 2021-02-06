package ru.job4j.carmarket.servlet;

import ru.job4j.carmarket.model.Advertisement;
import ru.job4j.carmarket.model.Brand;
import ru.job4j.carmarket.store.HibernateAdvertisementStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 31.01.2021
 */
public class IndexServlet extends HttpServlet {
    private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

    private boolean isToday(Advertisement ad) {
        return System.currentTimeMillis() - ad.getCreated().getTime() < MILLIS_PER_DAY;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter = req.getParameter("filter");
        List<Advertisement> ads = HibernateAdvertisementStore.instOf().getAll(Advertisement.class);
        if (filter != null && !filter.equals("all")) {
            if (filter.equals("day")) {
                ads = ads.stream().filter(this::isToday).collect(Collectors.toList());
            } else if (filter.equals("photo")) {
                ads = ads.stream().filter(ad -> !ad.getPhotoName().equals("empty.jpg"))
                .collect(Collectors.toList());
            } else {
                ads = ads.stream().filter(ad -> ad.getBrand().getName().equals(filter)).collect(Collectors.toList());
            }
        }
        req.setAttribute("ads", ads);
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.setAttribute("brands", HibernateAdvertisementStore.instOf().getAll(Brand.class));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int adId = Integer.parseInt(req.getParameter("ad_id"));
        Advertisement ad = HibernateAdvertisementStore.instOf().getAdById(adId);
        ad.setStatus(false);
        HibernateAdvertisementStore.instOf().update(ad);
        resp.sendRedirect("index.do");
    }
}
