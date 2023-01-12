package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AddBookingDto {
    @NotNull
    private Long itemId;
    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;
}
