package ru.job4j.carmarket.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.carmarket.model.*;
import ru.job4j.carmarket.store.HibernateAdvertisementStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 31.01.2021
 */
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("brands", HibernateAdvertisementStore.instOf().getAll(Brand.class));
        req.setAttribute("colors", HibernateAdvertisementStore.instOf().getAll(Color.class));
        req.setAttribute("categories", HibernateAdvertisementStore.instOf().getAll(Category.class));
        req.setAttribute("years", HibernateAdvertisementStore.instOf().getAll(Year.class));
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        String photoName = null;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext context = this.getServletConfig().getServletContext();
        File repository = (File) context.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        Map<String, String> params = new HashMap<>();
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("photos");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    photoName = item.getName();
                    File file = new File(folder + File.separator + photoName);
                    try (FileOutputStream output = new FileOutputStream(file)) {
                        output.write(item.getInputStream().readAllBytes());
                    }
                } else {
                    params.put(item.getFieldName(), item.getString());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Brand brand = new Brand(Integer.parseInt(params.get("brand")));
        String model = params.get("model");
        Color color = new Color(Integer.parseInt(params.get("color")));
        Category category = new Category(Integer.parseInt(params.get("category")));
        Year year = new Year(Integer.parseInt(params.get("year")));
        int miles = Integer.parseInt(params.get("miles"));
        int price = Integer.parseInt(params.get("price"));
        if (photoName == null) {
            photoName = "empty.jpg";
        }
        HibernateAdvertisementStore.instOf().create(
                new Advertisement(
                        brand,
                        model,
                        color,
                        category,
                        year,
                        miles,
                        price,
                        user,
                        photoName
                )
        );
        resp.sendRedirect("index.do");
    }
}
