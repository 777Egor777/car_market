package ru.job4j.carmarket.store;

import org.junit.Test;
import ru.job4j.carmarket.model.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HibernateAdvertisementStoreTest {

    @Test
    public void create() throws Exception {
        try (AdvertisementStore store = HibernateAdvertisementStore.instanceForTest();
             UserStore userStore = HibernateUserStore.testInstance()) {
            Brand brand = new Brand("Audi");
            brand = store.create(brand);
            Color color = new Color("White");
            color = store.create(color);
            Category category = new Category("lightweight");
            category = store.create(category);
            Year year = new Year(2014);
            year = store.create(year);
            User user = new User("egor@yandex.ru", "123", "Egor");
            user = userStore.add(user);
            Advertisement ad = new Advertisement(
                    brand,
                    "A6",
                    color,
                    category,
                    year,
                    100,
                    1000000,
                    user,
                    "audi_photo"
            );
            ad = store.create(ad);
            assertThat(ad, is(store.getAdById(ad.getId())));
        }
    }
}