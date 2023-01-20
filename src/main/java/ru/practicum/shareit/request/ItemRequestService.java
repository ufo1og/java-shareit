package ru.practicum.shareit.request;

import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.List;

public interface ItemRequestService {
    ItemRequestDto addNewRequest(ItemRequestDto itemRequestDto, Long userId);

    List<ItemRequestDto> getAllOwnRequests(Long id);

    List<ItemRequestDto> getAllRequestPaging(Long userId, Integer from, Integer size);

    ItemRequestDto getById(Long userId, Long requestId);
}
