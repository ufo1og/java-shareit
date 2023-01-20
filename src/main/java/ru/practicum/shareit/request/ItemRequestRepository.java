package ru.practicum.shareit.request;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ItemRequestRepository extends PagingAndSortingRepository<ItemRequest, Long> {
    List<ItemRequest> findAllByCreatorIdOrderByCreatedDesc(Long creatorId);

    List<ItemRequest> findAllByCreatorIdNotOrderByCreatedDesc(Long creatorId, Pageable pageable);
}
