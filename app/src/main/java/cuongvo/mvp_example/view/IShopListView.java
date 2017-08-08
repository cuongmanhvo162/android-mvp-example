package cuongvo.mvp_example.view;

import cuongvo.mvp_example.model.data.StoreListData;

/**
 * Created by cuongvo on 7/21/17.
 */

public interface IShopListView {
    void displayShopListError();

    void displayLoadMoreError();

    void displayShopList(StoreListData storeListData);

    void updateShopListAfterLoadMore(StoreListData storeListData);
}
