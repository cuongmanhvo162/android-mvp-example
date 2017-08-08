package cuongvo.mvp_example.presenter.interfaces;

/**
 * Created by cuongvo on 7/21/17.
 */

public interface ICityGuidePresenter {
    void getCityGuideList();
    void getCityGuideList(int page, boolean isLoadMore);
}
