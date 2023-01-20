package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByBookerIdOrderByStartDateDesc(Long bookerId);

    List<Booking> findAllByBookerIdAndEndDateBeforeOrderByStartDateDesc(Long userId, LocalDateTime date);

    List<Booking> findAllByBookerIdAndStartDateBeforeAndEndDateAfterOrderByStartDateDesc(Long userId,
                                                                                         LocalDateTime startBeforeDate,
                                                                                         LocalDateTime endAfterDate);

    List<Booking> findAllByBookerIdAndStartDateAfterOrderByStartDateDesc(Long userId, LocalDateTime date);

    List<Booking> findAllByBookerIdAndStatusOrderByStartDateDesc(Long userId, BookingStatus status);

    List<Booking> findAllByItemIdInOrderByStartDateDesc(List<Long> itemIds);

    List<Booking> findAllByItemIdInAndStartDateBeforeAndEndDateAfterOrderByStartDateDesc(List<Long> itemIds,
                                                                                         LocalDateTime startBeforeDate,
                                                                                         LocalDateTime endAfterDate);

    List<Booking> findAllByItemIdInAndEndDateBeforeAndStatusNotOrderByStartDateDesc(List<Long> itemIds,
                                                                                    LocalDateTime date,
                                                                                    BookingStatus status);

    List<Booking> findAllByItemIdInAndStartDateAfterOrderByStartDateDesc(List<Long> itemIds, LocalDateTime date);

    List<Booking> findAllByItemIdInAndStatusOrderByStartDateDesc(List<Long> itemIds, BookingStatus status);

    List<Booking> findAllByItemIdIn(List<Long> itemIds);

    List<Booking> findAllByItemIdAndBookerIdAndStatusAndStartDateBefore(Long itemId, Long bookerId,
                                                                        BookingStatus status, LocalDateTime date);

//    Long countAllByItemIdAndBookerIdAndStatusIsNotLike(Long itemId, Long bookerId, BookingStatus status);
}