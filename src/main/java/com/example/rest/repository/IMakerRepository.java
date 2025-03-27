package com.example.rest.repository;

import com.example.rest.entities.Maker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMakerRepository extends CrudRepository<Maker, Long> { }
