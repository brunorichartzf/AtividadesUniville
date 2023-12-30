package com.example.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Alunos {

    @Id
    private int Matricula;
    private String Nome;


    public int getMatricula() {return Matricula;}

    public void setMatricula(int matricula) {
        Matricula = matricula;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Override
    public String toString() {
        return "Alunos{" +
                "Matricula=" + Matricula +
                ", Nome='" + Nome + '\'' +
                '}';
    }
}
