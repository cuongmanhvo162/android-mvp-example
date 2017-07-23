package cuongvo.tinklabstest.presenter.util;

import rx.Scheduler;

/**
 * We use a scheduler to make testing our presenters easier
 * @author cuongvo
 */
public interface IScheduler {

    Scheduler mainThread();

    Scheduler backgroundThread();
}
