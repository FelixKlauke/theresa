package de.d3adspace.theresa.lifecycle;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.d3adspace.theresa.lifecycle.annotation.WarmUp;
import de.d3adspace.theresa.lifecycle.state.LifeCycleState;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Wrapper for a single objects life cycle.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class LifeCycle {

    /**
     * The instance this life cycle wrapper is operating on.
     */
    private final Object handle;
    /**
     * Holds references on the callback actions when a given state is entered.
     */
    private final Map<LifeCycleState, List<LifeCycleStateCallback>> lifeCycleCallbacks = Maps.newConcurrentMap();
    /**
     * The current life cycle state.
     */
    private LifeCycleState lifeCycleState;

    /**
     * Create a new life cycle wrapper by an instance and its starting state.
     *
     * @param handle         The instance.
     * @param lifeCycleState The life cycle state.
     */
    LifeCycle(Object handle, LifeCycleState lifeCycleState) {

        this.handle = handle;
        this.lifeCycleState = lifeCycleState;

        buildCallbackActions();
    }

    /**
     * Process a changing life cycle state.
     *
     * @param lifeCycleState Thew new state.
     */
    void processLifeCycleStateChange(LifeCycleState lifeCycleState) {

        // Execute callbacks for the given life cycle state
        List<LifeCycleStateCallback> callbacks = lifeCycleCallbacks.getOrDefault(lifeCycleState, Lists.newArrayList());
        callbacks.forEach(Runnable::run);

        // Set the new state
        this.lifeCycleState = lifeCycleState;
    }

    /**
     * Get the managed instance.
     *
     * @return The instance.
     */
    Object getHandle() {
        return handle;
    }

    /**
     * Scan for callback actions and build up callback registry.
     */
    private void buildCallbackActions() {

        Class<?> handleClass = handle.getClass();
        Method[] declaredMethods = handleClass.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            buildCallbackAction(declaredMethod);
        }
    }

    /**
     * Get the current life cycle state.
     *
     * @return The life cycle state.
     */
    public LifeCycleState getLifeCycleState() {

        return lifeCycleState;
    }

    private void buildCallbackAction(Method declaredMethod) {

        if (declaredMethod.isAnnotationPresent(PostConstruct.class)) {

            addCallbackMethod(LifeCycleState.CONSTRUCTION, new MethodLifeCycleStateCallback(declaredMethod));

        } else if (declaredMethod.isAnnotationPresent(PreDestroy.class)) {

            addCallbackMethod(LifeCycleState.DESTRUCTION, new MethodLifeCycleStateCallback(declaredMethod));

        } else if (declaredMethod.isAnnotationPresent(WarmUp.class)) {

            addCallbackMethod(LifeCycleState.WARM_UP, new MethodLifeCycleStateCallback(declaredMethod));
        }
    }

    /**
     * Add a callback for the given life cycle state.
     *
     * @param lifeCycleState The life cycle state.
     * @param callback       The callback.
     */
    private void addCallbackMethod(LifeCycleState lifeCycleState, LifeCycleStateCallback callback) {

        List<LifeCycleStateCallback> callbacks = lifeCycleCallbacks.computeIfAbsent(lifeCycleState, key -> Lists.newArrayList());
        callbacks.add(callback);
    }

    /**
     * Standard callback.
     */
    private abstract class LifeCycleStateCallback implements Runnable {

    }

    /**
     * A simple callback that will run a method.
     */
    private class MethodLifeCycleStateCallback extends LifeCycleStateCallback {

        /**
         * The method to invoke.
         */
        private final Method method;

        /**
         * Create a new callback instance by the method to run.
         *
         * @param method The method to run.
         */
        private MethodLifeCycleStateCallback(Method method) {
            this.method = method;
        }

        @Override
        public void run() {

            try {
                method.invoke(handle);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
