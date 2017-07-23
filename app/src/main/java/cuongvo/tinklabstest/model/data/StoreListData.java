package cuongvo.tinklabstest.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cuongvo on 7/22/17.
 */

public class StoreListData {

    @SerializedName("response")
    private List<StoreData> storeDataList;

    public List<StoreData> getStoreDataList() {
        return storeDataList;
    }

    public void setStoreDataList(List<StoreData> storeDataList) {
        this.storeDataList = storeDataList;
    }
}
