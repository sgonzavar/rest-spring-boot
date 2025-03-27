package com.example.rest.dao.implement;

import com.example.rest.dao.IMakerDAO;
import com.example.rest.entities.Maker;
import com.example.rest.repository.IMakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MakerImplementDAO implements IMakerDAO {

    @Autowired
    private IMakerRepository IMakerRepository;


    @Override
    public List<Maker> findAll() {
        return (List<Maker>) IMakerRepository.findAll();
    }

    @Override
    public Optional<Maker> findById(Long id) {
        return IMakerRepository.findById(id);
    }

    @Override
    public void save(Maker maker) {
        IMakerRepository.save(maker);
    }

    @Override
    public void deleteById(Long id) {
        IMakerRepository.deleteById(id);
    }
}
