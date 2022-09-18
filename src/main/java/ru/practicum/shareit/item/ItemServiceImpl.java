package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserStorage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemStorage itemStorage;
    private final UserStorage userStorage;

    @Override
    public ItemDto add(long userId, ItemDto itemDto) {
        User owner = userStorage.read(userId);
        Item item = ItemMapper.toItem(itemDto, owner);
        Item addedItem = itemStorage.add(item);
        return ItemMapper.toDto(addedItem);
    }

    @Override
    public ItemDto update(long userId, long itemId, ItemDto itemDto) {
        User owner = userStorage.read(userId);
        Item item = ItemMapper.toItem(itemDto, owner);
        item.setId(itemId);
        Item updatedItem = itemStorage.update(item);
        return ItemMapper.toDto(updatedItem);
    }

    @Override
    public ItemDto getById(long itemId) {
        Item item = itemStorage.getById(itemId);
        return ItemMapper.toDto(item);
    }

    @Override
    public List<ItemDto> getByUser(long userId) {
        User owner = userStorage.read(userId);
        return itemStorage.getByUser(userId, owner).stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> searchItems(String text) {
        if (text.isBlank()) {
            return Collections.emptyList();
        }
        return itemStorage.searchItems(text).stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }
}
