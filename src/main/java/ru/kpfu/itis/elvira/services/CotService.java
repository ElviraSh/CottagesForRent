package ru.kpfu.itis.elvira.services;


import ru.kpfu.itis.elvira.entity.Cottages;

import java.util.List;

public interface CotService {


    Cottages getCar(String text);

    List<Cottages> findAll();

    void save(Cottages car);

    void remove(Long id);

    Cottages getCarById(String id);
}
