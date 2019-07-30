package de.d3adspace.theresa.example;

import de.d3adspace.theresa.core.Theresa;
import de.d3adspace.theresa.core.TheresaFactory;
import de.d3adspace.theresa.lifecycle.listener.LifeCycleTransactionListener;

/**
 * @author Felix Klauke (info@felix-klauke.de)
 */
public class TheresaExample {

    public static void main(String[] args) {

        Theresa theresa = TheresaFactory.create();

        LifeCycleTransactionListener lifeCycleTransactionListener = lifeCycleTransaction -> {
            System.out.println("Detected life cycle transaction of " + lifeCycleTransaction.getInstance() + " from state "
                    + lifeCycleTransaction.getCurrentState() + " into " + lifeCycleTransaction.getNewState() + ".");
        };
        theresa.registerLifeCycleTransactionListener(lifeCycleTransactionListener);

        theresa.startLifeCycle();

        ExampleApplication instance = theresa.getInstance(ExampleApplication.class);
        instance.action();

        theresa.stopLifeCycle();
    }
}
