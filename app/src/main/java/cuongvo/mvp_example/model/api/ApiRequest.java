package cuongvo.mvp_example.model.api;

import cuongvo.mvp_example.model.data.StoreListData;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by cuongvo on 7/22/17.
 */
public interface ApiRequest {

    @GET
    Observable<StoreListData> requestStoreList(@Url String url);
}
