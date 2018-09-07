package de.d3adspace.theresa.example.manager;

import com.google.inject.ImplementedBy;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ImplementedBy(SomethingManagerImpl.class)
public interface SomethingManager {

    void doSomething();
}
