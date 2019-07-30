package de.d3adspace.theresa.lifecycle.listener;

import com.google.inject.spi.ProvisionListener;
import de.d3adspace.theresa.lifecycle.LifeCycleManager;

/**
 * {@link ProvisionListener} that detects instances that are provisioned to notify the life cycle manager about the new
 * instance.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public class LifeCycleProvisionListener implements ProvisionListener {

    /**
     * The life cycle listener.
     */
    private final LifeCycleManager lifeCycleManager;

    /**
     * Create a new provision listener by the underlying life cycle manager.
     *
     * @param lifeCycleManager The life cycle manager.
     */
    public LifeCycleProvisionListener(LifeCycleManager lifeCycleManager) {

        this.lifeCycleManager = lifeCycleManager;
    }

    @Override
    public <T> void onProvision(ProvisionInvocation<T> provisionInvocation) {

        // The provisioned instance
        T provision = provisionInvocation.provision();

        // Let life cycle manager manage the instance
        lifeCycleManager.manageInstance(provision);
    }
}
