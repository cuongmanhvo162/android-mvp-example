package cuongvo.mvp_example.presenter.interfaces;

/**
 * Created by cuongvo on 7/21/17.
 */

public interface IShopPresenter {
    void getShopListData();
    void getShopListData(int page, boolean isLoadMore);
}
