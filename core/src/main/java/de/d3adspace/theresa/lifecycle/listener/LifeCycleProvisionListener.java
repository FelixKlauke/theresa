package de.d3adspace.theresa.lifecycle.listener;

import com.google.inject.spi.ProvisionListener;
import de.d3adspace.theresa.lifecycle.LifeCycleFactory;
import de.d3adspace.theresa.lifecycle.LifeCycleRegistry;

/**
 * {@link ProvisionListener} that detects instances that are provisioned to notify the life cycle
 * manager about the new instance.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public record LifeCycleProvisionListener(
    LifeCycleFactory lifeCycleFactory,
    LifeCycleRegistry lifeCycleRegistry
) implements ProvisionListener {

  @Override
  public <T> void onProvision(ProvisionInvocation<T> provisionInvocation) {
    // The provisioned instance
    var provision = provisionInvocation.provision();
    // Let life cycle manager manage the instance
    var lifeCycle = lifeCycleFactory.createLifeCycle(provision);
    lifeCycleRegistry.saveLifecycle(provision, lifeCycle);
  }
}
