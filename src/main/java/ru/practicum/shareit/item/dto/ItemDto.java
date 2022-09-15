package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.practicum.shareit.item.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ItemDto {
    private final Long id;
    @NotBlank(groups = {Create.class})
    private final String name;
    @NotBlank(groups = {Create.class})
    private final String description;
    @NotNull(groups = {Create.class})
    private final Boolean available;
}
