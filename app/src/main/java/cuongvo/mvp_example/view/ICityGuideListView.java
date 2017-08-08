package cuongvo.mvp_example.view;

import cuongvo.mvp_example.model.data.StoreListData;

/**
 * Created by cuongvo on 7/21/17.
 */

public interface ICityGuideListView {
    void displayCityListError();

    void displayLoadMoreError();

    void displayEmptyList();

    void displayCityList(StoreListData storeListDataList);

    void updateCityListAfterLoadMore(StoreListData storeListData);
}
