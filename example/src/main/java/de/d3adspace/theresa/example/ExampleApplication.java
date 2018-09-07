package de.d3adspace.theresa.example;

import com.google.inject.ImplementedBy;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ImplementedBy(ExampleApplicationImpl.class)
public interface ExampleApplication {

    void action();
}
