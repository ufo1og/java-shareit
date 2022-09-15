package ru.practicum.shareit.item.model;

import lombok.*;
import ru.practicum.shareit.user.User;

/**
 * TODO Sprint add-controllers.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private User owner;
}
