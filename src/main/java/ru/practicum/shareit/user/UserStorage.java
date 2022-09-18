package ru.practicum.shareit.user;

import java.util.List;

public interface UserStorage {
    User create(User user);

    User read(long id);

    User update(User user);

    User delete(long id);

    List<User> readAll();
}
