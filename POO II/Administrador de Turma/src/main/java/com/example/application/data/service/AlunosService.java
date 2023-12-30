package com.example.application.data.service;

import com.example.application.data.entity.Alunos;
import com.example.application.data.repository.AlunosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import com.example.application.data.entity.Alunos;
import com.example.application.data.repository.AlunosRepository;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.application.persistencia.Services;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunosService {

    private final AlunosRepository repository;

    public AlunosService(AlunosRepository repository) {
        this.repository = repository;
    }

    public Optional<Alunos> get(Long id) {
        return repository.findById(id);
    }

    public Alunos update(Alunos entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Alunos> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Alunos> list(Pageable pageable, Specification<Alunos> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    @Autowired
    private Services services;

    public List<Alunos> findAll(){
        return services.obterTodosAlunos();
    }

}
