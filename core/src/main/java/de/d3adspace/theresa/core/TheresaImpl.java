package de.d3adspace.theresa.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import de.d3adspace.theresa.lifecycle.LifeCycleManager;
import de.d3adspace.theresa.lifecycle.listener.LifeCycleTransactionListener;
import de.d3adspace.theresa.lifecycle.module.LifecycleModule;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author Felix Klauke (info@felix-klauke.de)
 */
public class TheresaImpl implements Theresa {

  /**
   * The logger for all general actions.
   */
  private final Logger logger = Logger.getLogger(TheresaImpl.class.getName());

  /**
   * The life cycle manager.
   */
  private final LifeCycleManager lifeCycleManager;

  /**
   * The external parent injector.
   */
  private final Injector externalParentInjector;

  /**
   * The external effective injector.
   */
  private Injector externalEffectiveInjector;

  TheresaImpl(LifeCycleManager lifeCycleManager, Module... externalParentModules) {
    this.lifeCycleManager = lifeCycleManager;
    this.externalParentInjector = Guice.createInjector(new LifecycleModule(lifeCycleManager));
    this.externalEffectiveInjector = externalParentInjector
      .createChildInjector(externalParentModules);
  }

  @Override
  public void startLifeCycle() {

    logger.info("Starting theresa life cycle.");
    lifeCycleManager.startLifeCycle();
  }

  @Override
  public <T> T getInstance(Class<? extends T> instanceClass) {

    Objects.requireNonNull(instanceClass, "Instance class should be null.");

    // Retrieve instance from external effective injector.
    return externalEffectiveInjector.getInstance(instanceClass);
  }

  @Override
  public void injectMembers(Object object) {

    Objects.requireNonNull(object, "Can't inject memmbers into a null object.");

    externalEffectiveInjector.injectMembers(object);
  }

  @Override
  public void stopLifeCycle() {

    logger.info("Stopping theresa life cycle.");
    lifeCycleManager.stopLifeCycle();
  }

  @Override
  public void registerLifeCycleTransactionListener(
    LifeCycleTransactionListener lifeCycleTransactionListener) {

    Objects.requireNonNull(lifeCycleTransactionListener,
      "Can't register a null instance as listener.");

    lifeCycleManager.registerLifeCycleTransactionListener(lifeCycleTransactionListener);
  }
}
