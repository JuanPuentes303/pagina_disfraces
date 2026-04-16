package com.disfracesrivera.backend.service;

import com.disfracesrivera.backend.model.Disfraz;
import com.disfracesrivera.backend.repository.DisfrazRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DisfrazService {

    @Autowired
    private DisfrazRepository disfrazRepository;

    public List<Disfraz> obtenerAleatorios() {
        List<Disfraz> disfraces = disfrazRepository.findAll();
        Collections.shuffle(disfraces);

        return disfraces.stream().limit(6).toList();
    }
}