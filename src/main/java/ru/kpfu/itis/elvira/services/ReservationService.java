package ru.kpfu.itis.elvira.services;

import ru.kpfu.itis.elvira.entity.Reservation;

import java.util.List;


public interface ReservationService {

    List<Reservation> findAll();

    void save(Reservation reservation);

    void remove(String id);

    Reservation findById(String id);

    void update(Reservation reservation);
}
