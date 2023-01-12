package ru.practicum.shareit.item;

import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.BookingMapper;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public class ItemMapper {
    public static ItemDto toItemDto(Item item, Booking lastBooking, Booking nextBooking, List<CommentDto> comments) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                BookingMapper.toBookingInfoDto(lastBooking),
                BookingMapper.toBookingInfoDto(nextBooking),
                comments
        );
    }

    public static Item toItem(ItemDto itemDto, Long ownerId) {
        return new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getAvailable(),
                ownerId
        );
    }
}
