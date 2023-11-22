package insercoes;

import javax.persistence.Entity;

@Entity
public class Aplicacao extends EntradaDados{
	protected float juros;

	public Aplicacao() {
        // construtor sem argumentos
    }
	public Aplicacao(String nome, String dataIn, String descricao, Float valor, float juros) {
		super(nome, dataIn, descricao, valor);
		this.tipo = "Aplicacao";
		this.juros = juros;
	}
	
	
	
}
