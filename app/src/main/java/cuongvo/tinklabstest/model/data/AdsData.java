package cuongvo.tinklabstest.model.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cuongvo on 7/22/17.
 */

public class AdsData {

    @SerializedName("id")
    private int id;

    @SerializedName("image_url")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
