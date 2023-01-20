package ru.practicum.shareit.item.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ITEMS")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "is_available", nullable = false)
    private Boolean available;
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;
    @Column(name = "request_id")
    private Long requestId;
}
