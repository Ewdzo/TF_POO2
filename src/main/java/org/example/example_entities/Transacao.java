package org.example.example_entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Transacao {
    @Id
    int idTransacao;

    @ManyToOne
    ContaBancaria conta;

    @ManyToOne
    Cliente titular;

    @ManyToMany
    @JoinTable(
 	    name = "TransacaoDespesa",
		joinColumns = @JoinColumn(name = "transacao"),
	    inverseJoinColumns = @JoinColumn(name = "despesa")
	)
    List<CategoriaDeDespesa> categorias;
    
    double valor;
    String tipo;
    Date data;

    public Transacao(int idTransacao, ContaBancaria conta, double valor, String tipo, Date data){
        this.idTransacao = idTransacao;
        this.conta = conta;
        this.titular = conta.titular;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    public void setCategorias(List<CategoriaDeDespesa> categorias) {
        this.categorias = categorias;
    }
}
