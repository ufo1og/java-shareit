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
    private Long id;
    private String name;
    private String description;
    @Column(name = "is_available")
    private Boolean available;
    @Column(name = "owner_id")
    private Long ownerId;
//    private Long requestId;
}
