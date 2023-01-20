package ru.practicum.shareit.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@Getter
@Setter
public class CommentCreateDto {
    @NotBlank
    private String text;
}
