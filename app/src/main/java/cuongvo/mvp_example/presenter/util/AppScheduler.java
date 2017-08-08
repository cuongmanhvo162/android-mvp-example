package cuongvo.mvp_example.presenter.util;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Schedulers for the app.
 *
 * @author cuongvo
 */
public class AppScheduler implements IScheduler {

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.io();
    }
}
