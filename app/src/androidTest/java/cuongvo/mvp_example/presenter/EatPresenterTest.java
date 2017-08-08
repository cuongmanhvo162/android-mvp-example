package cuongvo.mvp_example.presenter;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;

import cuongvo.mvp_example.base.BaseTest;
import cuongvo.mvp_example.util.TestScheduler;
import cuongvo.mvp_example.model.api.ApiClient;
import cuongvo.mvp_example.model.api.ApiRequest;
import cuongvo.mvp_example.model.data.StoreListData;
import cuongvo.mvp_example.view.IEatListView;
import rx.Observable;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by cuongvo on 7/23/17.
 */
@RunWith(AndroidJUnit4.class)
public class EatPresenterTest extends BaseTest {

    private EatPresenter mEatPresenter;
    private ApiRequest mApiRequest;

    @Before
    public void setup(){
        mEatPresenter = new EatPresenter(mContext, new TestScheduler());
        mEatPresenter.setEatListView(Mockito.mock(IEatListView.class));

        mApiRequest = Mockito.mock(ApiRequest.class);
        ApiClient.getInstance().create();
        ApiClient.getInstance().setApiRequest(mApiRequest);

    }

    @Test
    public void testSetEatListView() {
        EatPresenter eatPresenter = new EatPresenter(mContext, new TestScheduler());
        assertNull(eatPresenter.getEatListView());

        eatPresenter.setEatListView(mock(IEatListView.class));
        assertNotNull(eatPresenter.getEatListView());
    }

    @Test
    public void testEatPresenter() {
        // Request eat data list success
        when(mApiRequest.requestStoreList(anyString())).thenReturn(Observable.<StoreListData>never().just(getMockStoreDataListResults()));
        mEatPresenter.getEatListData();
        verify(mEatPresenter.getEatListView(), times(1)).displayEatListData(any(StoreListData.class));

        // Request eat data list with wrong page number
        when(mApiRequest.requestStoreList(anyString())).thenReturn(Observable.<StoreListData>error(new IOException()));
        mEatPresenter.getEatListData(-1, false);
        verify(mEatPresenter.getEatListView(), times(1)).displayEatListError();

        // Request eat data list with correct page number
        when(mApiRequest.requestStoreList(anyString())).thenReturn(Observable.<StoreListData>never().just(getMockStoreDataListResults()));
        mEatPresenter.getEatListData(1, false);
        verify(mEatPresenter.getEatListView(), atLeastOnce()).displayEatListData(any(StoreListData.class));
        verify(mEatPresenter.getEatListView(), atLeastOnce()).displayEatListError();
    }
}
