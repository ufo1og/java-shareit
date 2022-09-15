package ru.practicum.shareit.item;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exceptions.EntityNotFoundException;
import ru.practicum.shareit.exceptions.ForbiddenAccessException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ItemStorageImpl implements ItemStorage {
    private final Map<Long, Item> items = new HashMap<>();
    private long countId = 0;


    @Override
    public Item add(Item item) {
        item.setId(++countId);
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public Item update(Item item) {
        checkItemExistence(item.getId());
        Item updatedItem = items.get(item.getId());
        if (!Objects.equals(updatedItem.getOwner(), item.getOwner())) {
            throw new ForbiddenAccessException(
                    String.format("User with id = %s is not the owner of item with id = %s!",
                            item.getOwner().getId(),
                            updatedItem.getId()
                    ));
        }
        if (item.getName() != null) {
            updatedItem.setName(item.getName());
        }
        if (item.getDescription() != null) {
            updatedItem.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            updatedItem.setAvailable(item.getAvailable());
        }
        return updatedItem;
    }

    @Override
    public Item getById(long itemId) {
        checkItemExistence(itemId);
        return items.get(itemId);
    }

    @Override
    public List<Item> getByUser(long userId, User owner) {
        return items.values().stream()
                .filter(item -> Objects.equals(item.getOwner(), owner))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> searchItems(String text) {
        return items.values().stream()
                .filter(item -> isItemSatisfiesSearchCondition(item, text))
                .filter(Item::getAvailable)
                .collect(Collectors.toList());
    }

    private void checkItemExistence(long id) {
        if (!items.containsKey(id)) {
            throw new EntityNotFoundException(String.format("Item with id = %s not found!", id));
        }
    }

    private boolean isItemSatisfiesSearchCondition(Item item, String text) {
        String searchFor = text.toLowerCase();
        if (item.getName().toLowerCase().contains(searchFor)) {
            return true;
        }
        return item.getDescription().toLowerCase().contains(searchFor);
    }
}
