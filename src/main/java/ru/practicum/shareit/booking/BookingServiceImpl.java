package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dto.AddBookingDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.exceptions.ValidationFailException;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BoockingRepository boockingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public BookingDto add(Long userId, AddBookingDto bookingDto) {
        User booker = userRepository.findById(userId).get();
        Item bookingItem = itemRepository.findById(bookingDto.getItemId()).get();
        if (!bookingItem.getAvailable()) {
            throw new ValidationFailException("Booking item is not available!");
        }
        LocalDateTime now = LocalDateTime.now();
        if (bookingDto.getStart().isBefore(now)) {
            throw new ValidationFailException("Booking start date cant be in the past!");
        }
        if (bookingDto.getEnd().isBefore(now)) {
            throw new ValidationFailException("Booking end date cant be in the past!");
        }
        if (bookingDto.getEnd().isBefore(bookingDto.getStart())) {
            throw new ValidationFailException("Booking end date cant be before start date!");
        }
        if (Objects.equals(booker.getId(), bookingItem.getOwnerId())) {
            throw new ValidationFailException("You cant booking your own items!");
        }
        Booking booking = BookingMapper.toBooking(bookingDto, booker);
        Booking createdBooking = boockingRepository.save(booking);
        return BookingMapper.toBookingDto(createdBooking, booker, bookingItem);
    }
}
