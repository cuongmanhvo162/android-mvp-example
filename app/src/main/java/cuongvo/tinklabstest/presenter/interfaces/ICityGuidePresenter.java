package cuongvo.tinklabstest.presenter.interfaces;

import java.util.List;

import cuongvo.tinklabstest.model.data.StoreListData;

/**
 * Created by cuongvo on 7/21/17.
 */

public interface ICityGuidePresenter {
    void getCityGuideList();
    void getCityGuideList(int page, boolean isLoadMore);
}
