package de.d3adspace.theresa.core;

import com.google.inject.Module;
import de.d3adspace.theresa.lifecycle.LifeCycleManager;
import de.d3adspace.theresa.lifecycle.LifeCycleManagerImpl;

import java.util.logging.Logger;

/**
 * Standard factory for {@link Theresa} instances.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class TheresaFactory {

    private static final Logger LOGGER = Logger.getLogger(TheresaFactory.class.getSimpleName());

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
            LOGGER.warning("You're using zero effective modules. Are you sure about that?");
        }

        LifeCycleManager lifeCycleManager = new LifeCycleManagerImpl();
        return new TheresaImpl(lifeCycleManager, modules);
    }
}
