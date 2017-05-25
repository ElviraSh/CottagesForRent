package ru.kpfu.itis.elvira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.kpfu.itis.elvira.entity.Cottages;
import ru.kpfu.itis.elvira.repository.CotRepository;

import java.util.List;



@Service
public class CotServiceImpl implements CotService {


    @Autowired
    CotRepository cotRepository;


    @Override
    public Cottages getCar(String text) {
        return cotRepository.findByModel(text);
    }

    @Override
    public List<Cottages> findAll() {
        return cotRepository.findAll();
    }

    @Override
    public void save(Cottages car) {
        cotRepository.save(car);
    }

    @Override
    public void remove(Long id) {
        cotRepository.delete(id);
    }

    @Override
    public Cottages getCarById(String id) {
        return cotRepository.findOne(Long.valueOf(id));
    }
}
