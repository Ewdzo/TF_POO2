package org.example.example_entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PessoaJuridica extends Cliente {

    @Column(nullable=false, unique = true) 
    String CNPJ;

    String CPFMajoritario;
    String razaoSocial;
    Date dataDeAbertura;

    public PessoaJuridica(String CNPJ, String CPFMajoritario, String razaoSocial, Date dataDeAbertura) {
        super();
        this.CNPJ = CNPJ;
        this.CPFMajoritario = CPFMajoritario;
        this.razaoSocial = razaoSocial;
        this.dataDeAbertura = dataDeAbertura;
    }
}
