package cuongvo.mvp_example.presenter;

import android.content.Context;

import cuongvo.mvp_example.model.api.ApiClient;
import cuongvo.mvp_example.model.api.ApiController;
import cuongvo.mvp_example.model.data.StoreListData;
import cuongvo.mvp_example.view.IShopListView;
import cuongvo.mvp_example.presenter.interfaces.IShopPresenter;
import cuongvo.mvp_example.presenter.util.IScheduler;
import rx.Observer;

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
