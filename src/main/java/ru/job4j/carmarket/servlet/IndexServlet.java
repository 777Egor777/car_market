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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter = req.getParameter("filter");
        List<Advertisement> ads;
        if (filter == null || filter.equals("all")) {
            ads = HibernateAdvertisementStore.instOf().getAll(Advertisement.class);
        } else if (filter.equals("day")) {
            ads = HibernateAdvertisementStore.instOf().getTodayAds();
        } else if (filter.equals("photo")) {
            ads = HibernateAdvertisementStore.instOf().getAdsWithPhoto();
            System.out.println("ads with photo:\n" + ads);
        } else {
            ads = HibernateAdvertisementStore.instOf().getAdsByBrand(filter);
        }
        req.setAttribute("ads", ads);
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.setAttribute("brands", HibernateAdvertisementStore.instOf().getAll(Brand.class));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int adId = Integer.parseInt(req.getParameter("ad_id"));
        Advertisement ad = HibernateAdvertisementStore.instOf().getAdById(adId);
        ad.setStatus(false);
        HibernateAdvertisementStore.instOf().update(ad);
        resp.sendRedirect("index.do");
    }
}
