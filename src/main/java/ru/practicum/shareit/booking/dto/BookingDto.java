package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.practicum.shareit.booking.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BookingDto {
    Long id;
    LocalDateTime start;
    LocalDateTime end;
    BookingStatus status;
    User booker;
    Item item;
}
