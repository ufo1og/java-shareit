package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemRequestController {
    private final ItemRequestService itemRequestService;
    @PostMapping
    public ItemRequestDto addNewRequest(@RequestHeader("X-Sharer-User-Id") long userId,
                                        @Validated @RequestBody ItemRequestDto itemRequestDto) {
        return itemRequestService.addNewRequest(itemRequestDto, userId);
    }

    @GetMapping
    public List<ItemRequestDto> getAllOwnRequests(@RequestHeader("X-Sharer-User-Id") long userId) {
        return itemRequestService.getAllOwnRequests(userId);
    }

    @GetMapping("/all")
    public List<ItemRequestDto> getAllRequestsPaging(@RequestHeader("X-Sharer-User-Id") long userId,
                                                     @RequestParam(required = false, defaultValue = "0") Integer from,
                                                     @RequestParam(required = false) Optional<Integer> size) {
        if (size.isEmpty()) {
            return Collections.emptyList();
        }
        return itemRequestService.getAllRequestPaging(userId, from, size.get());
    }

    @GetMapping("/{requestId}")
    public ItemRequestDto getRequestById(@RequestHeader("X-Sharer-User-Id") long userId,
                                         @PathVariable("requestId") Long requestId) {
        return itemRequestService.getById(userId, requestId);
    }
}
