package cuongvo.mvp_example.util;


import cuongvo.mvp_example.presenter.util.IScheduler;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * This scheduler runs all work immediately on the current thread
 * This makes testing code that may be done asynchronously easier
 *
 * @author cuong vo
 */
public class TestScheduler implements IScheduler {
    @Override
    public Scheduler mainThread() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.immediate();
    }
}
