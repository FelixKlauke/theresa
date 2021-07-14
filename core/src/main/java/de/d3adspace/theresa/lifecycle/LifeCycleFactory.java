package de.d3adspace.theresa.lifecycle;

import com.google.common.base.Preconditions;
import de.d3adspace.theresa.lifecycle.state.LifeCycleState;

public final class LifeCycleFactory {

  private LifeCycleFactory() {
  }

  public static LifeCycleFactory create() {
    return new LifeCycleFactory();
  }

  public LifeCycle createLifeCycle(Object managedInstance) {
    Preconditions.checkNotNull(managedInstance, "Managed instance should not be null");
    var lifeCycle = LifeCycle.forManagedInstance(managedInstance);
    lifeCycle.updateState(LifeCycleState.CONSTRUCTION);
    return lifeCycle;
  }
}
