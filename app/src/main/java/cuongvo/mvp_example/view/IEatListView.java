package cuongvo.mvp_example.view;

import cuongvo.mvp_example.model.data.StoreListData;

/**
 * Created by cuongvo on 7/21/17.
 */

public interface IEatListView {
    void displayEatListError();

    void displayLoadMoreError();

    void displayEatListData(StoreListData storeListData);

    void updateCityListAfterLoadMore(StoreListData storeListData);
}

