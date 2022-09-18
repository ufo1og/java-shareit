package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.util.List;

public interface ItemStorage {
    Item add(Item item);

    Item update(Item item);

    Item getById(long itemId);

    List<Item> getByUser(long userId, User owner);

    List<Item> searchItems(String text);
}
