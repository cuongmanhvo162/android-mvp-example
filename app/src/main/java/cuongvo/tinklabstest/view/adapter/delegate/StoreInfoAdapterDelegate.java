package cuongvo.tinklabstest.view.adapter.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import cuongvo.tinklabstest.R;
import cuongvo.tinklabstest.model.data.StoreData;
import cuongvo.tinklabstest.model.util.Constant;
import cuongvo.tinklabstest.view.adapter.holder.StoreViewHolder;

/**
 * Created by cuongvo on 7/22/17.
 */

public class StoreInfoAdapterDelegate extends AdapterDelegate<List<StoreData>> {
    private Context mContext;

    public StoreInfoAdapterDelegate(Context context) {
        this.mContext = context;
    }

    @Override
    protected boolean isForViewType(@NonNull List<StoreData> items, int position) {
        StoreData storeData = items.get(position);

        if (storeData.getType().equals(Constant.TYPE_STORE)) {
            return true;
        } else {
            return false;
        }
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return new StoreViewHolder(inflater.inflate(R.layout.view_holder_store, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<StoreData> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        StoreViewHolder storeViewHolder = (StoreViewHolder) holder;

        StoreData storeData = items.get(position);
        if (storeData != null) {
            if (storeData.getStoreInfoData() != null) {
                String imageUrl = storeData.getStoreInfoData().getImageUrl();
                if (!TextUtils.isEmpty(imageUrl)) {
                    Glide.with(mContext).load(imageUrl).centerCrop().into(storeViewHolder.mImageView);

                } else {

                }
                storeViewHolder.Description.setText(storeData.getStoreInfoData().getDescription());
                storeViewHolder.mTitle.setText(storeData.getStoreInfoData().getTitle());
            }
        }

    }
}
