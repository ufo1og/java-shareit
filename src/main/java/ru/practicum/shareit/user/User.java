package ru.practicum.shareit.user;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String email;
}
