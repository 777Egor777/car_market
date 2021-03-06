package ru.job4j.carmarket.store;

import ru.job4j.carmarket.model.User;

public interface UserStore extends AutoCloseable {
    User add(User user);
    User update(User user);
    User get(String login);
}
