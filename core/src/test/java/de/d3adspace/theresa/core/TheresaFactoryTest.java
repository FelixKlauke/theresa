package de.d3adspace.theresa.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * @author Felix Klauke (info@felix-klauke.de)
 */
class TheresaFactoryTest {

  @Test
  void testInstantiation() {

    Executable executable = TheresaFactory::new;
    assertThrows(AssertionError.class, executable);
  }

  @Test
  void testCreate() {

    Theresa theresa = TheresaFactory.create();
    assertNotNull(theresa, "Theresa instance may not be null.");
  }
}
