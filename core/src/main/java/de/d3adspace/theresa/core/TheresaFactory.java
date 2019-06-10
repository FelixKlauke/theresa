package de.d3adspace.theresa.core;

import com.google.inject.Module;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import de.d3adspace.theresa.lifecycle.LifeCycleManager;
import de.d3adspace.theresa.lifecycle.LifeCycleManagerImpl;

/**
 * Standard factory for {@link Theresa} instances.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class TheresaFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheresaFactory.class);

    TheresaFactory() {

        throw new AssertionError("You can't instantiate a factory.");
    }

    /**
     * Create a new theresa instance by it's underlying modules.
     *
     * @param modules The modules.
     *
     * @return The theresa instance.
     */
    public static Theresa create(Module... modules) {

        if (modules.length == 0) {
            LOGGER.warn("You're using zero effective modules. Are you sure about that?");
        }

        LifeCycleManager lifeCycleManager = new LifeCycleManagerImpl();
        return new TheresaImpl(lifeCycleManager, modules);
    }
}
