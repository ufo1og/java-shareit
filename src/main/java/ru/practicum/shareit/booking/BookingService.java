package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.AddBookingDto;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto add(Long userId, AddBookingDto bookingDto);

    BookingDto consider(Long userId, Long bookingId, Boolean approved);

    BookingDto getById(Long userId, Long bookingId);

    List<BookingDto> getAllBookerBookings(Long bookerId, String state);

    List<BookingDto> getAllOwnerBookings(Long ownerId, String state);
}
