package cuongvo.mvp_example.base;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import cuongvo.mvp_example.model.data.StoreData;
import cuongvo.mvp_example.model.data.StoreListData;

import static junit.framework.Assert.fail;

/**
 * Created by cuongvo on 7/22/17.
 */
@RunWith(AndroidJUnit4.class)
public class BaseTest {

    protected Context mContext;

    @Before
    public void setup() {
        mContext = InstrumentationRegistry.getContext();
    }

    /**
     * Wait for the specific time using {@link Thread#sleep(long)}
     *
     * @param milliseconds The time we want to wait for in millis
     */
    protected void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            fail();
        }
    }

    protected StoreListData getMockStoreDataListResults() {
        StoreListData searchResults = new StoreListData();
        List<StoreData> list = Arrays.asList(new StoreData(), new StoreData());
        searchResults.setStoreDataList(list);
        return searchResults;
    }
}
