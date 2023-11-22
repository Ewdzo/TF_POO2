package insercoes;

import javax.persistence.Entity;

@Entity
public class Receita extends EntradaDados{
	public Receita() {
        // construtor sem argumentos
    }
	public Receita(String nome, String dataIn, String descricao, Float valor) {
		super(nome, dataIn, descricao, valor);
		this.tipo = "Receita";
	}
	
}
