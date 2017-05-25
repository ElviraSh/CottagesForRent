package ru.kpfu.itis.elvira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.kpfu.itis.elvira.entity.Cottages;

public interface CotRepository extends JpaRepository<Cottages, Long> {


    Cottages findByModel(String text);
}
