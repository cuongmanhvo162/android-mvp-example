package cuongvo.tinklabstest.presenter;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;

import cuongvo.tinklabstest.base.BaseTest;
import cuongvo.tinklabstest.model.api.ApiClient;
import cuongvo.tinklabstest.model.api.ApiRequest;
import cuongvo.tinklabstest.model.data.StoreListData;
import cuongvo.tinklabstest.util.TestScheduler;
import cuongvo.tinklabstest.view.IShopListView;
import rx.Observable;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by cuongvo on 7/23/17.
 */
@RunWith(AndroidJUnit4.class)
public class ShopPresenterTest extends BaseTest {

    private ShopPresenter mShopPresenter;
    private ApiRequest mApiRequest;

    @Before
    public void setup(){
        mShopPresenter = new ShopPresenter(mContext, new TestScheduler());
        mShopPresenter.setShopListView(Mockito.mock(IShopListView.class));

        mApiRequest = Mockito.mock(ApiRequest.class);
        ApiClient.getInstance().create();
        ApiClient.getInstance().setApiRequest(mApiRequest);
    }

    @Test
    public void testSetCityGuideListView() {
        ShopPresenter shopPresenter = new ShopPresenter(mContext, new TestScheduler());
        assertNull(shopPresenter.getShopListView());

        shopPresenter.setShopListView(mock(IShopListView.class));
        assertNotNull(shopPresenter.getShopListView());

    }

    @Test
    public void testShopPresenter() {
        // Request shop data list with in incorrect page number
        when(mApiRequest.requestStoreList(anyString())).thenReturn(Observable.<StoreListData>error(new IOException()));
        mShopPresenter.getShopListData(-1, false);
        verify(mShopPresenter.getShopListView(), never()).displayShopList(any(StoreListData.class));
        verify(mShopPresenter.getShopListView(), times(1)).displayShopListError();

        // Request shop data list with correct page number
        when(mApiRequest.requestStoreList(anyString())).thenReturn(Observable.<StoreListData>never().just(getMockStoreDataListResults()));
        mShopPresenter.getShopListData(1, false);
        verify(mShopPresenter.getShopListView(), atLeastOnce()).displayShopListError();
        verify(mShopPresenter.getShopListView(), times(1)).displayShopList(any(StoreListData.class));

    }
}
