package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.practicum.shareit.user.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class UserDto {
    @Positive
    private final Long id;
    @NotBlank(groups = {Create.class})
    private final String name;
    @NotBlank(groups = {Create.class})
    private final String email;
}
