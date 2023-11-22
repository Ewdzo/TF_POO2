package insercoes;

import javax.persistence.Entity;

@Entity
public class Despesa extends EntradaDados{

	public Despesa() {
        // construtor sem argumentos
    }
	public Despesa(String nome, String dataIn, String descricao, Float valor) {
		super(nome, dataIn, descricao, valor);
		this.tipo = "Despesa";
	}

}
