package cuongvo.mvp_example.presenter.interfaces;

/**
 * Created by cuongvo on 7/21/17.
 */

public interface IEatPresenter {
    void getEatListData();

    void getEatListData(int page, boolean isLoadMore);
}
