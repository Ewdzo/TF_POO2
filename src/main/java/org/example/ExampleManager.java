package org.example;

import java.util.GregorianCalendar;
import java.util.List;

import org.example.example_entities.CategoriaDeDespesa;
import org.example.example_entities.ContaBancaria;
import org.example.example_entities.PessoaFisica;
import org.example.example_entities.PessoaJuridica;
import org.example.example_entities.Transacao;

import jakarta.persistence.EntityManager;

public abstract class ExampleManager {
    static void populateViaHibernate(){
        PessoaFisica pf1 = new PessoaFisica("345.678.901-23", "Beatriz Alves", new GregorianCalendar(1978, 12, 9).getTime());
        PessoaFisica pf2 = new PessoaFisica("789.012.345-67", "Lucas Ferreira", new GregorianCalendar(1996, 06, 17).getTime());
        PessoaFisica pf3 = new PessoaFisica("234.567.890-12", "Camila Mendes", new GregorianCalendar(2001, 11, 03).getTime());
        PessoaFisica pf4 = new PessoaFisica("678.901.234-56", "André Moreira", new GregorianCalendar(1985, 8, 25).getTime());
        PessoaFisica pf5 = new PessoaFisica("123.456.789-90", "Lúcia dos Santos", new GregorianCalendar(1990, 04, 12).getTime());

        PessoaJuridica pj1 = new PessoaJuridica("88.999.111/0001-54", "123.456.789-09", "Soluções TechMaster Ltda", new GregorianCalendar(2017, 8, 17).getTime());
        PessoaJuridica pj2 = new PessoaJuridica("32.165.498/0001-45", "987.654.321-21", "ABC Distribuidora de Produtos Alimentícios S/A", new GregorianCalendar(2022, 03, 29).getTime());
        PessoaJuridica pj3 = new PessoaJuridica("55.666.777/0001-33", "555.666.777-33", "Papelaria Criativa Expresso Ltda", new GregorianCalendar(2019, 11, 10).getTime());
        PessoaJuridica pj4 = new PessoaJuridica("98.765.432/0001-21", "321.654.987-45", "Consultoria Global de Negócios S/A", new GregorianCalendar(2020, 6, 5).getTime());
        PessoaJuridica pj5 = new PessoaJuridica("12.345.678/0001-90", "888.999.111-54", "Mega Construções Silva & Filhos Ltda", new GregorianCalendar(2021, 9, 22).getTime());

        ContaBancaria cb1 = new ContaBancaria(1001, pf1, 45372.89);
        ContaBancaria cb2 = new ContaBancaria(2002, pf2, 102689.45);
        ContaBancaria cb3 = new ContaBancaria(3003, pj3, 230890.12);
        ContaBancaria cb4 = new ContaBancaria(4004, pj4, 705984.27);
        ContaBancaria cb5 = new ContaBancaria(5005, pf5, 238600.30);

        Transacao t1 = new Transacao(75892413, cb1, 5000.50, "Deposito", new GregorianCalendar(2023, 06, 9).getTime());
        Transacao t2 = new Transacao(21560987, cb1, -10750.25, "Saque", new GregorianCalendar(2023, 07, 1).getTime());
        Transacao t3 = new Transacao(93217766, cb3, 2500.00, "PIX", new GregorianCalendar(2023, 8, 23).getTime());
        Transacao t4 = new Transacao(46782345, cb5, -5000.50, "Saque", new GregorianCalendar(2023, 9, 14).getTime());
        Transacao t5 = new Transacao(85462109, cb2, 8600.30, "Deposito", new GregorianCalendar(2023, 10, 5).getTime());

        CategoriaDeDespesa c1 = new CategoriaDeDespesa("Saúde", 0.00);
        CategoriaDeDespesa c2 = new CategoriaDeDespesa("Lazer", 0.15);
        CategoriaDeDespesa c3 = new CategoriaDeDespesa("Cultura", 0.1);
        CategoriaDeDespesa c4 = new CategoriaDeDespesa("Comida", 0.05);
        CategoriaDeDespesa c5 = new CategoriaDeDespesa("Despesas", 0.1);

        t1.setCategorias(List.of(c5));
        t2.setCategorias(List.of(c2, c3, c4));
        t3.setCategorias(List.of(c5));
        t4.setCategorias(List.of(c1));
        t5.setCategorias(List.of(c5));


        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();
            
            em.persist(pf1);
            em.persist(pf2);
            em.persist(pf3);
            em.persist(pf4);
            em.persist(pf5);
    
            em.persist(pj1);
            em.persist(pj2);
            em.persist(pj3);
            em.persist(pj4);
            em.persist(pj5);
    
            em.persist(cb1);
            em.persist(cb2);
            em.persist(cb3);
            em.persist(cb4);
            em.persist(cb5);

            em.persist(t1);
            em.persist(t2);
            em.persist(t3);
            em.persist(t4);
            em.persist(t5);

            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.persist(c5);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    static void busqueContaBancaria(int id) {
        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();
            
            ContaBancaria cb = em.find(ContaBancaria.class, id);

            System.out.print("Conta Bancária Encontrada: " + cb.saldo);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    static void deletaContaBancaria(int id) {
        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();
            
            ContaBancaria cb = em.find(ContaBancaria.class, id);
            em.remove(cb);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    static void zeraSaldoContaBancaria(int id) {
        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();
            
            ContaBancaria cb = em.find(ContaBancaria.class, id);
            cb.saldo = 0.0;

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
