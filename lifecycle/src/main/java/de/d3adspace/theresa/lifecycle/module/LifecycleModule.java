package de.d3adspace.theresa.lifecycle.module;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import de.d3adspace.theresa.lifecycle.LifeCycleManager;
import de.d3adspace.theresa.lifecycle.listener.LifeCycleProvisionListener;

/**
 * Module for life cycle bindings.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public class LifecycleModule extends AbstractModule {

    /**
     * The life cycle manager.
     */
    private final LifeCycleManager lifeCycleManager;

    /**
     * Create a new module by its manager.
     *
     * @param lifeCycleManager The manager.
     */
    public LifecycleModule(LifeCycleManager lifeCycleManager) {
        this.lifeCycleManager = lifeCycleManager;
    }

    @Override
    protected void configure() {

        bind(LifeCycleManager.class).toInstance(lifeCycleManager);
        bindListener(Matchers.any(), new LifeCycleProvisionListener(lifeCycleManager));
    }
}
