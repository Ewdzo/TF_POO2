package org.example;

import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        HibernateManager.registerActor("000.000.000-01", "Enzo Weder", new GregorianCalendar(1980, 11, 12).getTime(), "path");
    }
    
}