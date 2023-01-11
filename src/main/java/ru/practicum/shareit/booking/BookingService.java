package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.AddBookingDto;
import ru.practicum.shareit.booking.dto.BookingDto;

public interface BookingService {

    BookingDto add(Long userId, AddBookingDto bookingDto);

    BookingDto consider(Long userId, Long bookingId, Boolean approved);
}
