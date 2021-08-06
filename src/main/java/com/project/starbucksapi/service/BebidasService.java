package com.project.starbucksapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.starbucksapi.model.Bebidas;
import com.project.starbucksapi.repository.BebidasRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BebidasService {

	private final BebidasRepository bebidasRepository;

    public List<Bebidas> findAll() {
        return bebidasRepository.findAll();
    }
    
    public void save(Bebidas bebida) {
         bebidasRepository.save(bebida);
    }
}
