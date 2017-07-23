package cuongvo.tinklabstest.presenter;

import android.content.Context;
import android.util.Log;

import cuongvo.tinklabstest.model.data.StoreListData;
import cuongvo.tinklabstest.presenter.interfaces.IShopPresenter;
import cuongvo.tinklabstest.model.api.ApiClient;
import cuongvo.tinklabstest.model.api.ApiController;
import cuongvo.tinklabstest.presenter.util.IScheduler;
import cuongvo.tinklabstest.view.IShopListView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cuongvo on 7/21/17.
 *
 * The presenter of Shop view.
 * Handle all of logic for the view.
 */

public class ShopPresenter implements IShopPresenter {
    private Context mContext;
    private IScheduler mIScheduler;

    private IShopListView mIShopListView;

    public ShopPresenter(Context context, IScheduler iScheduler) {
        this.mContext = context;
        this.mIScheduler = iScheduler;
    }

    public void setShopListView(IShopListView shopListView) {
        this.mIShopListView = shopListView;
    }

    public IShopListView getShopListView() {
        return this.mIShopListView;
    }


    @Override
    public void getShopListData() {
        String url = ApiController.getShopList();
        doRequestShopListData(url, false);
    }

    @Override
    public void getShopListData(int page, final boolean isLoadMore) {
        String url = ApiController.getShopListWithPageNumber(page);
        doRequestShopListData(url, isLoadMore);
    }

    private void doRequestShopListData(String url, final boolean isLoadMore) {
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
                            mIShopListView.displayLoadMoreError();
                        } else {
                            mIShopListView.displayShopListError();
                        }
                    }

                    @Override
                    public void onNext(StoreListData storeListData) {
                        if (isLoadMore) {
                            mIShopListView.updateShopListAfterLoadMore(storeListData);
                        } else {
                            mIShopListView.displayShopList(storeListData);
                        }
                    }
                });
    }
}
