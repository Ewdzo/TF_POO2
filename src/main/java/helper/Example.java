package helper;

import java.util.GregorianCalendar;

public class Example {
    public void Populate(){
        HibernateManager.registerActor("000.000.000-01", "Ryan Gosling", new GregorianCalendar(2001, 11, 12).getTime(), "path");
    }
}
