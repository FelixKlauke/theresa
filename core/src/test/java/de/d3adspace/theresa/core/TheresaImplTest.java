package de.d3adspace.theresa.core;

import com.google.inject.AbstractModule;
import de.d3adspace.theresa.lifecycle.LifeCycleManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class TheresaImplTest {

    @Mock
    private LifeCycleManager lifeCycleManager;
    @Mock
    private TestService testService;

    private TheresaImpl theresa;

    @BeforeEach
    void setUp() {

        theresa = new TheresaImpl(lifeCycleManager, new TestModule());
    }

    @Test
    void testStartLifeCycle() {

        theresa.startLifeCycle();

        verify(lifeCycleManager).startLifeCycle();
    }

    @Test
    void testGetInstance() {

        TestService instance = theresa.getInstance(TestService.class);

        assertEquals(testService, instance, "service instance differs!");
    }

    @Test
    void testStopLifeCycle() {

        theresa.stopLifeCycle();

        verify(lifeCycleManager).stopLifeCycle();
    }

    public interface TestService {

    }

    public class TestModule extends AbstractModule {

        @Override
        protected void configure() {

            bind(TestService.class).toInstance(testService);
        }
    }
}