package ru.kpfu.itis.elvira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.elvira.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
