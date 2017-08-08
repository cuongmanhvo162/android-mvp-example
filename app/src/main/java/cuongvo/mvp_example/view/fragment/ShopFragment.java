package cuongvo.mvp_example.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cuongvo.mvp_example.BaseFragment;
import cuongvo.mvp_example.model.data.StoreListData;
import cuongvo.mvp_example.view.component.ViewList;
import cuongvo.mvp_example.R;
import cuongvo.mvp_example.presenter.ShopPresenter;
import cuongvo.mvp_example.presenter.util.AppScheduler;
import cuongvo.mvp_example.view.IShopListView;

/**
 * Created by cuongvo on 7/21/17.
 */

public class ShopFragment extends BaseFragment implements IShopListView, ViewList.OnLoadMoreListener {

    @BindView(R.id.fragment_shop_list)
    ViewList mViewList;

    private ShopPresenter mShopPresenter;

    public ShopFragment() {
        mFragmentType = FragmentType.ShopFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, view);

        mViewList.setOnLoadMoreListener(this);

        mShopPresenter = new ShopPresenter(mContext, new AppScheduler());
        mShopPresenter.setShopListView(this);
        mShopPresenter.getShopListData();

        return view;
    }

    @Override
    public void displayShopListError() {

    }

    @Override
    public void displayLoadMoreError() {
        mViewList.hideLoadMoreProgress();
    }

    @Override
    public void displayShopList(StoreListData storeListData) {
        mViewList.setStoreList(storeListData.getStoreDataList());
        mViewList.displayList();
    }

    @Override
    public void updateShopListAfterLoadMore(StoreListData storeListData) {
        mViewList.updateStoreList(storeListData.getStoreDataList());
        mViewList.hideLoadMoreProgress();
    }

    @Override
    public void onLoadMore(int page) {
        if (!mViewList.isLoadMore()) {
            mViewList.displayLoadMoreProgress();
            mShopPresenter.getShopListData(page, true);
        }
    }
}
