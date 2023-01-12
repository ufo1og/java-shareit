package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.practicum.shareit.booking.Booking;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BookingInfoDto {
    private final Long id;
    private final Long bookerId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public BookingInfoDto(Booking booking) {
        this.id = booking.getId();
        this.bookerId = booking.getBookerId();
        this.startDate = booking.getStartDate();
        this.endDate = booking.getEndDate();
    }
}

