package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoockingRepository extends JpaRepository<Booking, Long> {
}
