package ru.kpfu.itis.elvira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.elvira.entity.Reservation;
import ru.kpfu.itis.elvira.repository.ReservationRepository;

import java.util.List;


@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;


    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void remove(String id) {
        reservationRepository.delete(Long.valueOf(id));
    }

    @Override
    public Reservation findById(String id) {
        return reservationRepository.findOne(Long.valueOf(id));
    }

    @Override
    public void update(Reservation reservation) {
        reservationRepository.save(reservation);
    }


}
