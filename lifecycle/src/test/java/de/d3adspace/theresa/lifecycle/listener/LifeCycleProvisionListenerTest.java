package de.d3adspace.theresa.lifecycle.listener;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.inject.spi.ProvisionListener;
import de.d3adspace.theresa.lifecycle.LifeCycleManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Felix Klauke (info@felix-klauke.de)
 */
@ExtendWith(MockitoExtension.class)
class LifeCycleProvisionListenerTest {

  @Mock
  private LifeCycleManager lifeCycleManager;
  @Mock
  private ProvisionListener.ProvisionInvocation<Object> provisionInvocation;
  @Mock
  private Object provision;

  private LifeCycleProvisionListener provisionListener;

  @BeforeEach
  void setUp() {

    // Create the provision manager
    provisionListener = new LifeCycleProvisionListener(lifeCycleManager);
  }

  @Test
  void testOnProvision() {

    // Given
    when(provisionInvocation.provision()).thenReturn(provision);

    // When
    provisionListener.onProvision(provisionInvocation);

    // Then
    verify(lifeCycleManager).manageInstance(provision);
  }
}
