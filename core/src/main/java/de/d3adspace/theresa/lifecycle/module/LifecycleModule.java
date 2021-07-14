package de.d3adspace.theresa.lifecycle.module;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import de.d3adspace.theresa.lifecycle.LifeCycleFactory;
import de.d3adspace.theresa.lifecycle.LifeCycleRegistry;
import de.d3adspace.theresa.lifecycle.listener.LifeCycleProvisionListener;

/**
 * Module for life cycle bindings.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public final class LifecycleModule extends AbstractModule {
  private final LifeCycleFactory lifeCycleFactory;
  private final LifeCycleRegistry lifeCycleRegistry;

  public LifecycleModule(LifeCycleFactory lifeCycleFactory, LifeCycleRegistry lifeCycleRegistry) {
    this.lifeCycleFactory = lifeCycleFactory;
    this.lifeCycleRegistry = lifeCycleRegistry;
  }

  @Override
  protected void configure() {
    var lifeCycleProvisionListener = new LifeCycleProvisionListener(
        lifeCycleFactory,
        lifeCycleRegistry
    );
    bindListener(Matchers.any(), lifeCycleProvisionListener);
  }
}
