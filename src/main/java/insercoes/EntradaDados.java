package insercoes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class EntradaDados {
	@Id
	protected String nome;
	protected String dataIn;
	protected String dataOut;
	protected String descricao;
	protected String tipo;
	protected Float valor;
	
	 public EntradaDados() {
	        // construtor sem argumentos
	    }
	
	public EntradaDados(String nome, String dataIn, String descricao, Float valor) {
		super();
		this.nome = nome;
		this.dataIn = dataIn;
		this.descricao = descricao;
		this.valor = valor;
		dataOut = null;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataIn() {
		return dataIn;
	}
	public void setDataIn(String dataIn) {
		this.dataIn = dataIn;
	}
	public String getDataOut() {
		return dataOut;
	}
	public void setDataOut(String dataOut) {
		this.dataOut = dataOut;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Float getValor() {
		return valor;
	}
	
	public void setValor(Float valor) {
		this.valor = valor;
	}
}
