package org.example.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PessoaFisica extends Cliente {  

    @Column(nullable=false, unique = true) 
    String CPF;

    String nome;
    Date dataDeNascimento;

    public PessoaFisica(String CPF, String nome, Date dataDeNascimento) {
        super();
        this.CPF = CPF;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    };
};
