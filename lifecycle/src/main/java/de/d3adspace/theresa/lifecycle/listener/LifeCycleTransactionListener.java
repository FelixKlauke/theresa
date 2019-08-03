package de.d3adspace.theresa.lifecycle.listener;

import de.d3adspace.theresa.lifecycle.transaction.LifeCycleTransaction;

/**
 * Listen for life cycle states on objects.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public interface LifeCycleTransactionListener {

  /**
   * Callback for life cycle transactions.
   *
   * @param lifeCycleTransaction The life cycle transaction.
   */
  void onLifeCycleTransaction(LifeCycleTransaction lifeCycleTransaction);
}
