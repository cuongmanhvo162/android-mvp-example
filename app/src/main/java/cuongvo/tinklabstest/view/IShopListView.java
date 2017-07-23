package cuongvo.tinklabstest.view;

import cuongvo.tinklabstest.model.data.StoreListData;

/**
 * Created by cuongvo on 7/21/17.
 */

public interface IShopListView {
    void displayShopListError();

    void displayLoadMoreError();

    void displayShopList(StoreListData storeListData);

    void updateShopListAfterLoadMore(StoreListData storeListData);
}
