package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByBookerIdOrderByStartDateDesc(Long bookerId);

    List<Booking> findAllByBookerIdAndEndDateBeforeOrderByStartDateDesc(Long userId, LocalDateTime date);

    List<Booking> findAllByBookerIdAndEndDateAfterOrderByStartDateDesc(Long userId, LocalDateTime date);

    List<Booking> findAllByBookerIdAndStartDateAfterOrderByStartDateDesc(Long userId, LocalDateTime date);

    List<Booking> findAllByBookerIdAndStatusOrderByStartDateDesc(Long userId, BookingStatus status);

    List<Booking> findAllByItemIdInOrderByStartDateDesc(List<Long> itemIds);

    List<Booking> findAllByItemIdInAndEndDateBeforeOrderByStartDateDesc(List<Long> itemIds, LocalDateTime date);

    List<Booking> findAllByItemIdInAndEndDateAfterOrderByStartDateDesc(List<Long> itemIds, LocalDateTime date);

    List<Booking> findAllByItemIdInAndStartDateAfterOrderByStartDateDesc(List<Long> itemIds, LocalDateTime date);

    List<Booking> findAllByItemIdInAndStatusOrderByStartDateDesc(List<Long> itemId, BookingStatus status);
}