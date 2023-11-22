package relatorio;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import insercoes.Aplicacao;
import insercoes.Despesa;
import insercoes.Emprestimo;
import insercoes.Receita;

public class RelatorioDiario implements Relatorio {
	private String dataRef;
	private String texto;

	public RelatorioDiario(String dataRef) {
		this.dataRef = dataRef;
	}


	public String gerarRelatorio(String dataRef) {
		HashMap<String, Float> entradas = new HashMap<String, Float>();
		HashMap<String, Float> saidas = new HashMap<String, Float>();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhoFinalPOO");
		EntityManager em = emf.createEntityManager();

		try {
			String jpql = "SELECT a FROM Aplicacao a WHERE a.dataIn LIKE :diaMesAno";
			TypedQuery<Aplicacao> query = em.createQuery(jpql, Aplicacao.class);
			query.setParameter("diaMesAno", "%" + dataRef + "%");
			List<Aplicacao> resultados = query.getResultList();
			for (Aplicacao aplicacao : resultados) {
				entradas.put(aplicacao.getNome(), aplicacao.getValor());
			}

			jpql = "SELECT a FROM Despesa a WHERE a.dataIn LIKE :diaMesAno";
			TypedQuery<Despesa> query2 = em.createQuery(jpql, Despesa.class);
			query2.setParameter("diaMesAno", "%" + dataRef + "%");
			List<Despesa> resultados2 = query2.getResultList();
			for (Despesa despesa : resultados2) {
				saidas.put(despesa.getNome(), despesa.getValor());
			}

			jpql = "SELECT a FROM Emprestimo a WHERE a.dataIn LIKE :diaMesAno";
			TypedQuery<Emprestimo> query3 = em.createQuery(jpql, Emprestimo.class);
			query3.setParameter("diaMesAno", "%" + dataRef + "%");
			List<Emprestimo> resultados3 = query3.getResultList();
			for (Emprestimo emprestimo : resultados3) {
				Float valorMes = emprestimo.getValorData(dataRef);
				if (valorMes > 0) {
					saidas.put(emprestimo.getNome(), emprestimo.getParcela());
				}
			}

			jpql = "SELECT a FROM Receita a WHERE a.dataIn LIKE :diaMesAno";
			TypedQuery<Receita> query4 = em.createQuery(jpql, Receita.class);
			query4.setParameter("diaMesAno", "%" + dataRef + "%");
			List<Receita> resultados4 = query4.getResultList();
			for (Receita receita : resultados4) {
				entradas.put(receita.getNome(), receita.getValor());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		Float total = 0f;
		texto = "Dia: " + dataRef + "\n";
		texto += "Entradas:\n";
		for (String chave : entradas.keySet()) {
			Float valor = entradas.get(chave);
			texto += chave + ": " + Float.toString(valor) + "\n";
			total += valor;
		}
		texto += "Saídas:\n";
		for (String chave : saidas.keySet()) {
			Float valor = saidas.get(chave);
			texto += chave + ": " + Float.toString(valor) + "\n";
			total -= valor;
		}
		texto += "Total: " + Float.toString(total)+"\n";
		return texto;
	}
}
