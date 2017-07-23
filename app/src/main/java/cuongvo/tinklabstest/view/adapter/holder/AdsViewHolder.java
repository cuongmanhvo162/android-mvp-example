package cuongvo.tinklabstest.view.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cuongvo.tinklabstest.R;

/**
 * Created by cuongvo on 7/22/17.
 * <p>
 * This view holder for Ads data type.
 * It only use to display Ads image.
 */
public class AdsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_holder_ads)
    public ImageView mAdsImage;

    public AdsViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
