package com.example.application.data.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class Boletim {

    @Id
    private int Matricula;
    private String Nome;
    private int Faltas;
    private float N1;
    private float N2;
    private float N3;
    private float N4;
    private boolean Aprovacao;

    public int getMatricula() {
        return Matricula;
    }

    public void setMatricula(int matricula) {
        Matricula = matricula;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public float getN1() {
        return N1;
    }

    public void setN1(float nota1) {
        N1 = nota1;
    }

    public float getN2() {
        return N2;
    }

    public void setN2(float nota2) {
        N2 = nota2;
    }

    public float getN3() {
        return N3;
    }

    public void setN3(float nota3) {
        N3 = nota3;
    }

    public float getN4() {
        return N4;
    }

    public void setN4(float nota4) {
        N4 = nota4;
    }

    public int getFaltas() {
        return Faltas;
    }

    public void setFaltas(int falta) {
        Faltas = falta;
    }

    public boolean getAprovacao() {
        return Aprovacao;
    }

    public void setAprovacao(boolean aprovacao) {
        Aprovacao = aprovacao;
    }

    @Override
    public String toString() {
        return "Boletim{" +
                "Matricula=" + Matricula +
                ", Nome='" + Nome + '\'' +
                ", Faltas=" + Faltas +
                ", N1=" + N1 +
                ", N2=" + N2 +
                ", N3=" + N3 +
                ", N4=" + N4 +
                ", Aprovacao=" + Aprovacao +
                '}';
    }
}
