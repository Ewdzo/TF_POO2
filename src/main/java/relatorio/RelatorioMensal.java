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

public class RelatorioMensal implements Relatorio {
	String dataRef;
	String texto;
	
	public RelatorioMensal(String dataRef) {
		this.dataRef = dataRef;
	}

	public RelatorioMensal() {}

	public String gerarRelatorio(String dataRef) {
		HashMap<String,Float> entradas = new HashMap<String,Float>();
		HashMap<String,Float> saidas = new HashMap<String,Float>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhoFinalPOO");
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT a FROM Aplicacao a WHERE a.dataIn LIKE :mesAno";
            TypedQuery<Aplicacao> query = em.createQuery(jpql, Aplicacao.class);
            query.setParameter("mesAno", "%" + dataRef + "%");
            List<Aplicacao> resultados = query.getResultList();
            for (Aplicacao aplicacao : resultados) {
            	entradas.put(aplicacao.getNome(), aplicacao.getValor());
            }
            
            jpql = "SELECT a FROM Despesa a WHERE a.dataIn LIKE :mesAno";
            TypedQuery<Despesa> query2 = em.createQuery(jpql, Despesa.class);
            query2.setParameter("mesAno", "%" + dataRef + "%");
            List<Despesa> resultados2 = query2.getResultList();
            for (Despesa aplicacao : resultados2) {
            	saidas.put(aplicacao.getNome(), aplicacao.getValor());
            }
            
            jpql = "SELECT b FROM Emprestimo b";
            TypedQuery<Emprestimo> query3 = em.createQuery(jpql, Emprestimo.class);
            List<Emprestimo> resultados3 = query3.getResultList();
            for (Emprestimo aplicacao : resultados3) {
            	Float valorMes = aplicacao.getValorMes(dataRef);
            	
            	
            	if(valorMes > 0) {
            		saidas.put(aplicacao.getNome(), aplicacao.getParcela());
            		
            	}
            }
            
            jpql = "SELECT a FROM Receita a WHERE a.dataIn LIKE :mesAno";
            TypedQuery<Receita> query4 = em.createQuery(jpql, Receita.class);
            query4.setParameter("mesAno", "%" + dataRef + "%");
            List<Receita> resultados4 = query4.getResultList();
            for (Receita aplicacao : resultados4) {
            	entradas.put(aplicacao.getNome(), aplicacao.getValor());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        Float total = (float) 0;
        texto = "Mes: " + dataRef + "\n";
        texto += "Entradas: \n";
        for (String chave : entradas.keySet()) {
            Float valor = entradas.get(chave);
            texto += chave + ": " + Float.toString(valor) + "\n";
            total+= valor;
        }
        texto += "Saidas: \n";
        for (String chave : saidas.keySet()) {
            Float valor = saidas.get(chave);
            texto += chave + ": " + Float.toString(valor) + "\n";
            total -= valor;
        }
        texto += "Total: " + Float.toString(total)+ "\n";
		return texto;
	}

}
