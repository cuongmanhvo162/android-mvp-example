package cuongvo.mvp_example.model.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cuongvo on 7/22/17.
 */

public class StoreData {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("category")
    private String category;

    @SerializedName("store_info")
    private StoreInfoData storeInfoData;

    @SerializedName("ads")
    private AdsData adsData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public StoreInfoData getStoreInfoData() {
        return storeInfoData;
    }

    public void setStoreInfoData(StoreInfoData storeInfoData) {
        this.storeInfoData = storeInfoData;
    }

    public AdsData getAdsData() {
        return adsData;
    }

    public void setAdsData(AdsData adsData) {
        this.adsData = adsData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
