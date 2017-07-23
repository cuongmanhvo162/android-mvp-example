package cuongvo.tinklabstest.presenter;

import android.content.Context;
import android.util.Log;

import cuongvo.tinklabstest.model.data.StoreListData;
import cuongvo.tinklabstest.presenter.interfaces.ICityGuidePresenter;
import cuongvo.tinklabstest.model.api.ApiClient;
import cuongvo.tinklabstest.model.api.ApiController;
import cuongvo.tinklabstest.presenter.util.IScheduler;
import cuongvo.tinklabstest.view.ICityGuideListView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cuongvo on 7/21/17.
 */

public class CityGuidePresenter implements ICityGuidePresenter {

    private Context mContext;
    private IScheduler mIScheduler;

    private ICityGuideListView mICityGuideListView;

    public CityGuidePresenter(Context context, IScheduler iScheduler) {
        this.mContext = context;
        this.mIScheduler = iScheduler;
    }

    public ICityGuideListView getICityGuideListView() {
        return this.mICityGuideListView;
    }

    public void setICityGuideListView(ICityGuideListView cityGuideListView) {
        this.mICityGuideListView = cityGuideListView;
    }

    @Override
    public void getCityGuideList() {
        String url = ApiController.getCityGuideList();
        Log.d("CityGuidePresenter", "url " + url);

        doRequestCityGuideList(url, false);
    }

    @Override
    public void getCityGuideList(int page, final boolean isLoadMore) {
        String url = ApiController.getCityGuideListWithPageNumber(page);
        doRequestCityGuideList(url, isLoadMore);
    }

    private void doRequestCityGuideList(String url, final boolean isLoadMore) {
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
                            mICityGuideListView.displayLoadMoreError();
                        } else {
                            mICityGuideListView.displayCityListError();
                        }
                    }

                    @Override
                    public void onNext(StoreListData storeListData) {
                        if (isLoadMore) {
                            mICityGuideListView.updateCityListAfterLoadMore(storeListData);
                        } else {
                            mICityGuideListView.displayCityList(storeListData);
                        }
                    }
                });
    }
}
