package cuongvo.tinklabstest.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cuongvo.tinklabstest.BaseFragment;
import cuongvo.tinklabstest.R;
import cuongvo.tinklabstest.model.data.StoreListData;
import cuongvo.tinklabstest.presenter.CityGuidePresenter;
import cuongvo.tinklabstest.presenter.util.AppScheduler;
import cuongvo.tinklabstest.view.ICityGuideListView;
import cuongvo.tinklabstest.view.component.ViewList;
import cuongvo.tinklabstest.view.util.EndlessScrollingListener;

/**
 * Created by cuongvo on 7/21/17.
 */

public class CityGuideFragment extends BaseFragment implements ICityGuideListView, ViewList.OnLoadMoreListener {

    @BindView(R.id.fragment_city_guide_list)
    ViewList mViewList;

    private CityGuidePresenter mCityGuidePresenter;

    public CityGuideFragment() {
        mFragmentType = FragmentType.CityGuideFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, view);

        mViewList.setOnLoadMoreListener(this);

        mCityGuidePresenter = new CityGuidePresenter(mContext, new AppScheduler());
        mCityGuidePresenter.setICityGuideListView(this);
        mCityGuidePresenter.getCityGuideList();

        return view;
    }

    @Override
    public void displayCityListError() {

    }

    @Override
    public void displayLoadMoreError() {
        mViewList.hideLoadMoreProgress();
    }

    @Override
    public void displayEmptyList() {
        mViewList.displayMessage(getString(R.string.empty_message));
    }

    @Override
    public void displayCityList(StoreListData storeListDataList) {
        mViewList.setStoreList(storeListDataList.getStoreDataList());
        mViewList.displayList();
    }

    @Override
    public void updateCityListAfterLoadMore(StoreListData storeListData) {
        mViewList.updateStoreList(storeListData.getStoreDataList());
        mViewList.hideLoadMoreProgress();
    }

    @Override
    public void onLoadMore(int page) {
        if (!mViewList.isLoadMore()) {
            mViewList.displayLoadMoreProgress();
            mCityGuidePresenter.getCityGuideList(page, true);
        }
    }
}
