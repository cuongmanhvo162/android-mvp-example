package cuongvo.tinklabstest.view.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cuongvo.tinklabstest.R;

/**
 * Created by cuongvo on 7/22/17.
 * <p>
 * This is view holder for Store data type.
 * It contain Title, Description and Store image
 */
public class StoreViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.view_holder_store_title)
    public TextView mTitle;

    @BindView(R.id.view_holder_store_description)
    public TextView Description;

    @BindView(R.id.view_holder_store_image)
    public ImageView mImageView;

    public StoreViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
