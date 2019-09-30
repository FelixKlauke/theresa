package de.d3adspace.theresa.example;

import de.d3adspace.theresa.example.manager.SomethingManager;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The example default application implementation.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
@Singleton
public class DefaultApplication implements ExampleApplication {

  private final SomethingManager somethingManager;

  @Inject
  public DefaultApplication(SomethingManager somethingManager) {
    this.somethingManager = somethingManager;
  }

  @PostConstruct
  public void onPostConstruct() {

    System.out.println(DefaultApplication.class.getSimpleName() + "#" + "onPostConstruct()");
  }

  @Override
  public void action() {

    somethingManager.doSomething();
  }

  @PreDestroy
  public void onPreDestroy() {

    System.out.println(DefaultApplication.class.getSimpleName() + "#" + "onPreDestroy()");
  }
}
