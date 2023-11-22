package helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import insercoes.Aplicacao;
import insercoes.Despesa;
import insercoes.Emprestimo;
import insercoes.Receita;
import relatorio.FabricaRelatorio;
import relatorio.Relatorio;

//Facade
public class ComandosSistema {
	
	public static String gerarRelatorio(String tipo, String dataRef) {
		FabricaRelatorio manager = new FabricaRelatorio();
		Relatorio rl = manager.criarRelatorio(tipo, dataRef);
		return rl.gerarRelatorio(dataRef);
	}
	
	public static boolean inserirAplicacao(Aplicacao entrada) {
	    boolean r = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhoFinalPOO");
	    EntityManager em = emf.createEntityManager();
	
	    try {
	        // Verificar se o nome j� existe na tabela
	        String jpql = "SELECT COUNT(a) FROM Aplicacao a WHERE a.nome = :nome";
	        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
	        query.setParameter("nome", entrada.getNome());
	        Long count = query.getSingleResult();
	
	        if (count == 0) {
	            em.getTransaction().begin();
	            em.persist(entrada);
	            em.getTransaction().commit();
	            r = true;
	        } else {
	            r = false;
	        }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	        emf.close();
	    }
        return r;
    }
	
	
	public static boolean deletarAplicacao(String nome, String dataOut) {
		boolean r = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhoFinalPOO");
	    EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            String jpql = "SELECT a FROM Aplicacao a WHERE a.nome = :nome";
            TypedQuery<Aplicacao> query = em.createQuery(jpql, Aplicacao.class);
            query.setParameter("nome", nome);
            Aplicacao aplicacao = query.getSingleResult();

            if (aplicacao != null) {
                aplicacao.setDataOut(dataOut);
                em.getTransaction().commit();
                r = true;
            } else {
                r = false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return r;
    }
	
	public static boolean inserirDespesa(Despesa entrada) {
	    boolean r = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhoFinalPOO");
	    EntityManager em = emf.createEntityManager();
	
	    try {
	        // Verificar se o nome j� existe na tabela
	        String jpql = "SELECT COUNT(a) FROM Despesa a WHERE a.nome = :nome";
	        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
	        query.setParameter("nome", entrada.getNome());
	        Long count = query.getSingleResult();
	
	        if (count == 0) {
	            em.getTransaction().begin();
	            em.persist(entrada);
	            em.getTransaction().commit();
	            r = true;
	        } else {
	            r = false;
	        }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	        emf.close();
	    }
	    return r;
    }
	public static boolean deletarDespesa(String nome, String dataOut) {
		boolean r = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhoFinalPOO");
	    EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            String jpql = "SELECT a FROM Despesa a WHERE a.nome = :nome";
            TypedQuery<Despesa> query = em.createQuery(jpql, Despesa.class);
            query.setParameter("nome", nome);
            Despesa resultado = query.getSingleResult();

            if (resultado != null) {
                resultado.setDataOut(dataOut);
                em.getTransaction().commit();
                r = true;
            } else {
                r = false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return r;
	}
	
	public static boolean inserirEmprestimo(Emprestimo entrada) {
	    boolean r = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhoFinalPOO");
	    EntityManager em = emf.createEntityManager();
	
	    try {
	        // Verificar se o nome j� existe na tabela
	        String jpql = "SELECT COUNT(a) FROM Emprestimo a WHERE a.nome = :nome";
	        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
	        query.setParameter("nome", entrada.getNome());
	        Long count = query.getSingleResult();
	
	        if (count == 0) {
	            em.getTransaction().begin();
	            em.persist(entrada);
	            em.getTransaction().commit();
	            r = true;
	        } else {
	            r = false;
	        }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	        emf.close();
	    }
	    return r;
    }
	public static boolean deletarEmprestimo(String nome, String dataOut) {
		boolean r = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhoFinalPOO");
	    EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            String jpql = "SELECT a FROM Emprestimo a WHERE a.nome = :nome";
            TypedQuery<Emprestimo> query = em.createQuery(jpql, Emprestimo.class);
            query.setParameter("nome", nome);
            Emprestimo resultado = query.getSingleResult();

            if (resultado != null) {
                resultado.setDataOut(dataOut);
                em.getTransaction().commit();
                r = true;
            } else {
                r = false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return r;
	}
	
	public static boolean inserirReceita(Receita entrada) {
	    boolean r = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhoFinalPOO");
	    EntityManager em = emf.createEntityManager();
	
	    try {
	        // Verificar se o nome j� existe na tabela
	        String jpql = "SELECT COUNT(a) FROM Receita a WHERE a.nome = :nome";
	        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
	        query.setParameter("nome", entrada.getNome());
	        Long count = query.getSingleResult();
	
	        if (count == 0) {
	            em.getTransaction().begin();
	            em.persist(entrada);
	            em.getTransaction().commit();
	            r = true;
	        } else {
	            r = false;
	        }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	        emf.close();
	    }
	    return r;
    }
	public static boolean deletarReceita(String nome, String dataOut) {
		boolean r = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhoFinalPOO");
	    EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            String jpql = "SELECT a FROM Receita a WHERE a.nome = :nome";
            TypedQuery<Receita> query = em.createQuery(jpql, Receita.class);
            query.setParameter("nome", nome);
            Receita resultado = query.getSingleResult();

            if (resultado != null) {
                resultado.setDataOut(dataOut);
                em.getTransaction().commit();
                r = true;
            } else {
                r = false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return r;
	}
}
