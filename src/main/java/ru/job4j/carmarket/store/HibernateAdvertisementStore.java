package ru.job4j.carmarket.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.carmarket.model.Advertisement;
import ru.job4j.carmarket.model.Category;

import java.util.List;
import java.util.function.Function;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 01.02.2021
 */
public class HibernateAdvertisementStore implements AdvertisementStore, AutoCloseable {
    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private HibernateAdvertisementStore() {
    }

    private final static class Holder {
        private final static AdvertisementStore INSTANCE = new HibernateAdvertisementStore();
    }

    public static AdvertisementStore instOf() {
        return Holder.INSTANCE;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private <T> T tx(Function<Session, T> func) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = func.apply(session);
            tx.commit();
            return rsl;
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public <T> T create(T model) {
        return tx(session ->  {
            session.save(model);
            return model;
        });
    }

    @Override
    public <T> T update(T model) {
        return tx(session ->  {
            session.update(model);
            return model;
        });
    }

    @Override
    public <T> List<T> getAll(Class<T> cl) {
        return tx(session -> session.createQuery("from " + cl.getName()).list());
    }

    @Override
    public Advertisement getAdById(int id) {
        return tx(session -> session.get(Advertisement.class, id));
    }

    public static void main(String[] args) {
        System.out.println(instOf().getAll(Category.class));
    }
}
