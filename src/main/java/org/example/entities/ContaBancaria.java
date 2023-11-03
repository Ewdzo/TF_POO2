package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ContaBancaria {
    @Id
    int idConta;
    
    @OneToOne
    Cliente titular;
    
    public double saldo;

    public ContaBancaria(int idConta, Cliente titular, double saldo) {
        this.idConta = idConta;
        this.titular = titular;
        this.saldo = saldo;
    };
}
