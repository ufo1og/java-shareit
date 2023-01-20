package ru.practicum.shareit.request;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ITEM_REQUESTS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "created", nullable = false)
    private LocalDateTime created = LocalDateTime.now();
    @Column(name = "creator_id", nullable = false)
    private Long creatorId;
}
