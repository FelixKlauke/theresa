package de.d3adspace.theresa.lifecycle.listener;

import com.google.inject.spi.ProvisionListener;
import de.d3adspace.theresa.lifecycle.LifeCycle;
import de.d3adspace.theresa.lifecycle.LifeCycleFactory;
import de.d3adspace.theresa.lifecycle.LifeCycleRegistry;

/**
 * {@link ProvisionListener} that detects instances that are provisioned to notify the life cycle
 * manager about the new instance.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public class LifeCycleProvisionListener implements ProvisionListener {

  private final LifeCycleFactory lifeCycleFactory;
  private final LifeCycleRegistry lifeCycleRegistry;

  public LifeCycleProvisionListener(LifeCycleFactory lifeCycleFactory,
      LifeCycleRegistry lifeCycleRegistry) {
    this.lifeCycleFactory = lifeCycleFactory;
    this.lifeCycleRegistry = lifeCycleRegistry;
  }

  @Override
  public <T> void onProvision(ProvisionInvocation<T> provisionInvocation) {

    // The provisioned instance
    T provision = provisionInvocation.provision();

    // Let life cycle manager manage the instance
    LifeCycle lifeCycle = lifeCycleFactory.createLifeCycle(provision);
    lifeCycleRegistry.saveLifecycle(provision, lifeCycle);
  }
}
