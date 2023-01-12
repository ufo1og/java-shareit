package ru.practicum.shareit.item.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENTS")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_id")
    private Long itemId;
    private String text;
    @Column(name = "author_name")
    private String authorName;
    private LocalDateTime created;
}
