package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CategoriaDeDespesa {

    @Id
    String categoria;

    double imposto;

    public CategoriaDeDespesa(String categoria, double imposto) {
        this.categoria = categoria;
        this.imposto = imposto;
    };

}
