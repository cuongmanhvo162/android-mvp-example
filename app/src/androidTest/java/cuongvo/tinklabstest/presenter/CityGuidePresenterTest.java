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
import cuongvo.tinklabstest.view.ICityGuideListView;
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
 * Created by cuongvo on 7/22/17.
 */
@RunWith(AndroidJUnit4.class)
public class CityGuidePresenterTest extends BaseTest {

    private CityGuidePresenter mCityGuidePresenter;
    private ApiRequest mApiRequest;

    @Before
    public void setup(){
        mCityGuidePresenter = new CityGuidePresenter(mContext, new TestScheduler());
        mCityGuidePresenter.setICityGuideListView(Mockito.mock(ICityGuideListView.class));

        mApiRequest = Mockito.mock(ApiRequest.class);
        ApiClient.getInstance().create();
        ApiClient.getInstance().setApiRequest(mApiRequest);
    }

    @Test
    public void testSetCityGuideListView() {
        CityGuidePresenter cityGuidePresenter = new CityGuidePresenter(mContext, new TestScheduler());
        assertNull(cityGuidePresenter.getICityGuideListView());

        cityGuidePresenter.setICityGuideListView(mock(ICityGuideListView.class));
        assertNotNull(cityGuidePresenter.getICityGuideListView());

    }

    @Test
    public void testEatPresenter() {
        // Request city guide data success
        when(mApiRequest.requestStoreList(anyString())).thenReturn(Observable.<StoreListData>never().just(getMockStoreDataListResults()));
        mCityGuidePresenter.getCityGuideList();
        verify(mCityGuidePresenter.getICityGuideListView(), times(1)).displayCityList(any(StoreListData.class));

        // Request city guide data list with page number
        // Incorrect page number
        when(mApiRequest.requestStoreList(anyString())).thenReturn(Observable.<StoreListData>error(new IOException()));
        mCityGuidePresenter.getCityGuideList(-1, false);
        verify(mCityGuidePresenter.getICityGuideListView(), times(1)).displayCityListError();

        // Request city guide data list with page number
        // Correct page number
        when(mApiRequest.requestStoreList(anyString())).thenReturn(Observable.<StoreListData>never().just(getMockStoreDataListResults()));
        mCityGuidePresenter.getCityGuideList(1, false);
        verify(mCityGuidePresenter.getICityGuideListView(), atLeastOnce()).displayCityList(any(StoreListData.class));
    }
}
