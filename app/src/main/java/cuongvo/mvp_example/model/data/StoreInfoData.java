package cuongvo.mvp_example.model.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cuongvo on 7/22/17.
 */

public class StoreInfoData {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("image_url")
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
