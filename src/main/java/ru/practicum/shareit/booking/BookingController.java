package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.AddBookingDto;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingDto add(@RequestHeader("X-Sharer-User-Id") Long bookerId,
                          @Validated @RequestBody AddBookingDto bookingDto) {
        return bookingService.add(bookerId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto consider(@RequestHeader("X-Sharer-User-Id") Long ownerId,
                               @PathVariable("bookingId") Long bookingId,
                               @RequestParam("approved") Boolean approved) {
        return bookingService.consider(ownerId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getById(@RequestHeader("X-Sharer-User-Id") Long userId,
                              @PathVariable("bookingId") Long bookingId) {
        return bookingService.getById(userId, bookingId);
    }

    @GetMapping
    public List<BookingDto> getAllBookerBookings(@RequestHeader("X-Sharer-User-Id") Long bookerId,
                                   @RequestParam(required = false, defaultValue = "ALL") String state) {
        return bookingService.getAllBookerBookings(bookerId, state);
    }

    @GetMapping("/owner")
    public List<BookingDto> getAllOwnerBookings(@RequestHeader("X-Sharer-User-Id") Long ownerId,
                                                @RequestParam(required = false, defaultValue = "ALL") String state) {
        return bookingService.getAllOwnerBookings(ownerId, state);
    }
}
