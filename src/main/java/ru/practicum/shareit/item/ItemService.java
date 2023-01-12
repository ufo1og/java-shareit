package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.CommentCreateDto;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto add(long userId, ItemDto itemDto);

    ItemDto update(long userID, long itemId, ItemDto itemDto);

    ItemDto getById(long userId, long itemId);

    List<ItemDto> getByUser(long userId);

    List<ItemDto> searchItems(String text);

    CommentDto addComment(Long userId, Long itemId, CommentCreateDto text);
}
