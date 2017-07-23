package cuongvo.tinklabstest.view;

import cuongvo.tinklabstest.model.data.StoreListData;

/**
 * Created by cuongvo on 7/21/17.
 */

public interface IEatListView {
    void displayEatListError();

    void displayLoadMoreError();

    void displayEatListData(StoreListData storeListData);

    void updateCityListAfterLoadMore(StoreListData storeListData);
}

