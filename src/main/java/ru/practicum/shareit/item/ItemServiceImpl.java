package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exceptions.ForbiddenAccessException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ItemDto add(long userId, ItemDto itemDto) {
        User owner = userRepository.findById(userId).get();
        Item item = ItemMapper.toItem(itemDto, owner.getId());
        Item addedItem = itemRepository.save(item);
        log.info("Added new Item: {}.", addedItem);
        return ItemMapper.toDto(addedItem);
    }

    @Transactional
    @Override
    public ItemDto update(long userId, long itemId, ItemDto itemDto) {
        Item itemToUpdate = itemRepository.findById(itemId).get();
        if (itemToUpdate.getOwnerId() != userId) {
            throw new ForbiddenAccessException(String.format("User with id %s is not the owner!", userId));
        }
        Item item = ItemMapper.toItem(itemDto, userId);
        Optional.ofNullable(item.getName()).ifPresent(name -> {
            if (!name.isBlank()) {
                itemToUpdate.setName(name);
            }
        });
        Optional.ofNullable(item.getDescription()).ifPresent(description -> {
            if (!description.isBlank()) {
                itemToUpdate.setDescription(description);
            }
        });
        Optional.ofNullable(item.getAvailable()).ifPresent(itemToUpdate::setAvailable);
        Item updatedItem = itemRepository.save(itemToUpdate);
        log.info("Updated Item: {}.", updatedItem);
        return ItemMapper.toDto(updatedItem);
    }

    @Override
    public ItemDto getById(long itemId) {
        Item item = itemRepository.findById(itemId).get();
        log.info("Read Item: {}.", item);
        return ItemMapper.toDto(item);
    }

    @Override
    public List<ItemDto> getByUser(long userId) {
        List<Item> readItems = itemRepository.findAllByOwnerId(userId);
        log.info("Read Items: {}.", readItems);
        return readItems.stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> searchItems(String text) {
        if (text.isBlank()) {
            return Collections.emptyList();
        }
        List<Item> foundItems = itemRepository.findByNameOrDescriptionContainingIgnoreCaseAndAvailableTrue(text, text);
        log.info("Found Items: {}.", foundItems);
        return foundItems.stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }
}
