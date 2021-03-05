package ru.job4j.carmarket.model;

import ru.job4j.carmarket.store.AdvertisementStore;
import ru.job4j.carmarket.store.HibernateAdvertisementStore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 01.02.2021
 */
@Entity
@Table(name = "ads")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    private String model;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "year_val")
    private Year year;
    private int miles;
    private int price;
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
    private String photoName;
    private boolean status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Advertisement() {
    }

    public Advertisement(int id) {
        this.id = id;
    }

    public Advertisement(Brand brand, String model, Color color, Category category, Year year, int miles, int price, User user, String photoName) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.category = category;
        this.year = year;
        this.miles = miles;
        this.price = price;
        this.user = user;
        if (photoName == null) {
            photoName = "empty.jpg";
        }
        this.photoName = photoName;
        status = true;
        created = new Date(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Advertisement that = (Advertisement) o;

        if (id != that.id) {
            return false;
        }
        if (miles != that.miles) {
            return false;
        }
        if (price != that.price) {
            return false;
        }
        if (status != that.status) {
            return false;
        }
        if (!Objects.equals(brand, that.brand)) {
            return false;
        }
        if (!Objects.equals(model, that.model)) {
            return false;
        }
        if (!Objects.equals(color, that.color)) {
            return false;
        }
        if (!Objects.equals(category, that.category)) {
            return false;
        }
        if (!Objects.equals(year, that.year)) {
            return false;
        }
        if (!Objects.equals(user, that.user)) {
            return false;
        }
        if (!Objects.equals(photoName, that.photoName)) {
            return false;
        }
        return Objects.equals(created.getTime(), that.created.getTime());
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + miles;
        result = 31 * result + price;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (photoName != null ? photoName.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Advertisement.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("brand=" + brand)
                .add("model='" + model + "'")
                .add("color=" + color)
                .add("category=" + category)
                .add("year=" + year)
                .add("miles=" + miles)
                .add("price=" + price)
                .add("user=" + user)
                .add("photoName='" + photoName + "'")
                .add("status=" + status)
                .add("created=" + created)
                .toString();
    }

    public static void main(String[] args) {
        try (AdvertisementStore store = HibernateAdvertisementStore.instOf()) {
            for (int i = 1; i <= 3; ++i) {
                Advertisement ad = store.getAdById(i);
                ad.setCreated(new Date(System.currentTimeMillis()));
                store.update(ad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
