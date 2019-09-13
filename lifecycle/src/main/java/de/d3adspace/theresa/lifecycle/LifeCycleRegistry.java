package de.d3adspace.theresa.lifecycle;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class LifeCycleRegistry {

  /**
   * All instances we are currently managing.
   */
  private final Map<Object, LifeCycle> managedInstances = Maps.newConcurrentMap();

  private LifeCycleRegistry() {
  }

  public static LifeCycleRegistry create() {

    return new LifeCycleRegistry();
  }

  public void saveLifecycle(Object managedInstance, LifeCycle lifeCycle) {
    Preconditions.checkNotNull(managedInstance, "Managed object should not be null");
    Preconditions.checkNotNull(lifeCycle, "Life cycle should not be null");

    managedInstances.put(managedInstance, lifeCycle);
  }

  public Collection<LifeCycle> findAllLifeCycles() {

    Collection<LifeCycle> values = managedInstances.values();
    return Collections.unmodifiableCollection(values);
  }

  public void clearLifeCycles() {

    managedInstances.clear();
  }
}
