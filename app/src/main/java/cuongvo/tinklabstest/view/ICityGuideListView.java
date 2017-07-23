package cuongvo.tinklabstest.view;

import java.util.List;

import cuongvo.tinklabstest.model.data.StoreListData;

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
