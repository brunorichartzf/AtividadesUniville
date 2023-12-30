package com.example.application.data.service;

import com.example.application.data.entity.Alunos;
import com.example.application.data.entity.Boletim;

import java.util.List;
import java.util.Optional;

import com.example.application.data.repository.BoletimRepository;
import com.example.application.persistencia.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BoletimService {

    private final BoletimRepository repository;

    public BoletimService(BoletimRepository repository) {
        this.repository = repository;
    }

    public Optional<Boletim> get(Long id) {
        return repository.findById(id);
    }

    public Boletim update(Boletim entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Boletim> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Boletim> list(Pageable pageable, Specification<Boletim> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    @Autowired
    private Services services;

    public List<Boletim> findAll(){
        return services.obterTodoBoletim();
    }

}
