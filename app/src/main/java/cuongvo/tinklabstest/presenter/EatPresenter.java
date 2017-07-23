package cuongvo.tinklabstest.presenter;

import android.content.Context;
import android.util.Log;

import cuongvo.tinklabstest.model.data.StoreListData;
import cuongvo.tinklabstest.presenter.interfaces.IEatPresenter;
import cuongvo.tinklabstest.model.api.ApiClient;
import cuongvo.tinklabstest.model.api.ApiController;
import cuongvo.tinklabstest.presenter.util.IScheduler;
import cuongvo.tinklabstest.view.IEatListView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cuongvo on 7/21/17.
 */

public class EatPresenter implements IEatPresenter {

    private Context mContext;
    private IScheduler mIScheduler;

    private IEatListView mIEatListView;

    public EatPresenter(Context context, IScheduler iScheduler) {
        this.mContext = context;
        this.mIScheduler = iScheduler;
    }

    public void setEatListView(IEatListView eatListView) {
        this.mIEatListView = eatListView;
    }

    public IEatListView getEatListView() {
        return this.mIEatListView;
    }

    @Override
    public void getEatListData() {
        String url = ApiController.getEatList();
        doRequestEatListData(url, false);
    }

    @Override
    public void getEatListData(int page, final boolean isLoadMore) {
        String url = ApiController.getEatListWithPageNumber(page);
        doRequestEatListData(url, isLoadMore);
    }

    private void doRequestEatListData(String url, final boolean isLoadMore) {
        ApiClient apiClient = ApiClient.getInstance();
        apiClient.create();
        apiClient.getApiRequest().requestStoreList(url)
                .subscribeOn(mIScheduler.backgroundThread())
                .observeOn(mIScheduler.mainThread())
                .subscribe(new Observer<StoreListData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isLoadMore) {
                            mIEatListView.displayLoadMoreError();
                        } else {
                            mIEatListView.displayEatListError();
                        }
                    }

                    @Override
                    public void onNext(StoreListData storeListData) {
                        if (isLoadMore) {
                            mIEatListView.updateCityListAfterLoadMore(storeListData);
                        } else {
                            mIEatListView.displayEatListData(storeListData);
                        }
                    }
                });
    }
}
