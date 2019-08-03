package de.d3adspace.theresa.example.manager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Felix Klauke (info@felix-klauke.de)
 */
public class SomethingManagerImpl implements SomethingManager {

  @PostConstruct
  public void onPostConstruct() {

    System.out.println(SomethingManager.class.getSimpleName() + "#" + "onPostConstruct()");
  }

  @Override
  public void doSomething() {

    System.out.println("I do something!");
  }

  @PreDestroy
  public void onPreDestroy() {

    System.out.println(SomethingManager.class.getSimpleName() + "#" + "onPreDestroy()");
  }
}
