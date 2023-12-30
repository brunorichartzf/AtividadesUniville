package com.example.application.data.repository;

import com.example.application.data.entity.Boletim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BoletimRepository
        extends
            JpaRepository<Boletim, Long>,
            JpaSpecificationExecutor<Boletim> {

}
