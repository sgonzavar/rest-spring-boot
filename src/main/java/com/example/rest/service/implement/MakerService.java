package com.example.rest.service.implement;

import com.example.rest.dao.IMakerDAO;
import com.example.rest.entities.Maker;
import com.example.rest.service.IMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MakerService implements IMakerService {

    @Autowired
    private IMakerDAO iMakerDAO;

    @Override
    public List<Maker> findAll() {
        return iMakerDAO.findAll();
    }

    @Override
    public Optional<Maker> findById(Long id) {
        return iMakerDAO.findById(id);
    }

    @Override
    public void save(Maker maker) {
        iMakerDAO.save(maker);
    }

    @Override
    public void deleteById(Long id) {
        iMakerDAO.deleteById(id);
    }
}
