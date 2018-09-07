package de.d3adspace.theresa.example;

import de.d3adspace.theresa.core.Theresa;
import de.d3adspace.theresa.core.TheresaFactory;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class TheresaExample {

    public static void main(String[] args) {

        Theresa theresa = TheresaFactory.create();
        theresa.startLifeCycle();

        ExampleApplication instance = theresa.getInstance(ExampleApplication.class);
        instance.action();

        theresa.stopLifeCycle();
    }
}
