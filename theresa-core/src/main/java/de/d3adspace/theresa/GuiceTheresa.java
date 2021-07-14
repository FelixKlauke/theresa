package de.d3adspace.theresa;

import com.google.common.base.Preconditions;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import de.d3adspace.theresa.lifecycle.LifeCycleFactory;
import de.d3adspace.theresa.lifecycle.LifeCycleRegistry;
import de.d3adspace.theresa.lifecycle.module.LifecycleModule;
import de.d3adspace.theresa.lifecycle.phase.LifeCyclePhase;
import de.d3adspace.theresa.lifecycle.state.LifeCycleState;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/**
 * @author Felix Klauke (info@felix-klauke.de)
 */
public final class GuiceTheresa extends AbstractTheresa {

  /**
   * The logger for all general actions.
   */
  private final Logger logger = Logger.getLogger(GuiceTheresa.class.getName());

  /**
   * The reference to the life cycle phase.
   */
  private final AtomicReference<LifeCyclePhase> phaseReference = new AtomicReference<>(
      LifeCyclePhase.LATENT
  );

  /**
   * The life cycle registry that contains all life cycles.
   */
  private final LifeCycleRegistry lifeCycleRegistry;

  /**
   * The external effective injector.
   */
  private final Injector externalEffectiveInjector;

  GuiceTheresa(
      LifeCycleRegistry lifeCycleRegistry,
      LifeCycleFactory lifeCycleFactory,
      Module... externalParentModules
  ) {
    this.lifeCycleRegistry = lifeCycleRegistry;
    var externalParentInjector = Guice.createInjector(
        new LifecycleModule(lifeCycleFactory, lifeCycleRegistry)
    );
    this.externalEffectiveInjector = externalParentInjector.createChildInjector(
        externalParentModules
    );
  }

  @Override
  public void startLifeCycle() {
    logger.info("Starting theresa life cycle.");
    // Check if we even can start the life cycle management from here
    var validPhase = phaseReference.compareAndSet(
        LifeCyclePhase.LATENT,
        LifeCyclePhase.STARTING
    );
    if (!validPhase) {
      throw new IllegalStateException("Can't start life cycle from phase " + phaseReference.get());
    }
    var lifeCycles = lifeCycleRegistry.findAllLifeCycles();
    lifeCycles.forEach(lifeCycle -> lifeCycle.updateState(LifeCycleState.WARM_UP));
    // Setup is complete, let the running phase begin
    phaseReference.set(LifeCyclePhase.STARTED);
  }

  @Override
  public <T> T getInstance(Class<T> instanceClass) {
    Preconditions.checkNotNull(instanceClass, "The instance class should not be null");
    // Retrieve instance from external effective injector.
    return externalEffectiveInjector.getInstance(instanceClass);
  }

  @Override
  public void injectMembers(Object object) {
    Preconditions.checkNotNull(object, "Target object should not be null");
    externalEffectiveInjector.injectMembers(object);
  }

  @Override
  public void stopLifeCycle() {
    logger.info("Stopping theresa life cycle.");
    if (phaseReference.get() != LifeCyclePhase.STARTED) {
      throw new IllegalStateException("You can't stop a not started life cycle.");
    }

    var lifeCycles = lifeCycleRegistry.findAllLifeCycles();
    lifeCycles.forEach(lifeCycle -> lifeCycle.updateState(LifeCycleState.DESTRUCTION));
    lifeCycleRegistry.clearLifeCycles();
    phaseReference.set(LifeCyclePhase.STOPPED);
  }

  @Override
  protected Injector getInjector() {
    return externalEffectiveInjector;
  }
}
