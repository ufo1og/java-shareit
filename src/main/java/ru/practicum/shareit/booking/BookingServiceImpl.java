package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dto.AddBookingDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.exceptions.ForbiddenAccessException;
import ru.practicum.shareit.exceptions.ValidationFailException;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
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
        log.info("Created new Booking: {}.", createdBooking);
        return BookingMapper.toBookingDto(createdBooking, booker, bookingItem);
    }

    @Override
    @Transactional
    public BookingDto consider(Long ownerId, Long bookingId, Boolean approved) {
        Booking booking = boockingRepository.findById(bookingId).get();
        Item item = itemRepository.findById(booking.getItemId()).get();
        if (!Objects.equals(ownerId, item.getOwnerId())) {
            throw new ForbiddenAccessException("User is not the owner of the booking item!");
        }
        User booker = userRepository.findById(booking.getBookerId()).get();
        booking.setStatus(approved ? BookingStatus.APPROVED : BookingStatus.REJECTED);
        Booking updatedBooking = boockingRepository.save(booking);
        log.info("Updated Booking: {}.", updatedBooking);
        return BookingMapper.toBookingDto(updatedBooking, booker, item);
    }

    @Override
    public BookingDto getById(Long userId, Long bookingId) {
        Booking booking = boockingRepository.findById(bookingId).get();
        Item item = itemRepository.findById(booking.getItemId()).get();
        if (!Objects.equals(userId, booking.getBookerId()) && !Objects.equals(userId, item.getOwnerId())) {
            throw new ForbiddenAccessException("User is not the owner or booker in requested Booking!");
        }
        User booker = userRepository.findById(booking.getBookerId()).get();
        log.info("Read Booking: {}.", booking);
        return BookingMapper.toBookingDto(booking, booker, item);
    }
}
