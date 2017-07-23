package cuongvo.tinklabstest.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.List;

import cuongvo.tinklabstest.model.data.StoreData;
import cuongvo.tinklabstest.view.adapter.delegate.AdsInfoAdapterDelegate;
import cuongvo.tinklabstest.view.adapter.delegate.StoreInfoAdapterDelegate;

/**
 * Created by cuongvo on 7/22/17.
 *
 * This is list adapter for Store data.
 * This contains different kind of view for specified data type.
 * Using the delegate manager to bind the matched viewholder with data type in the list.
 */
public class StoreListAdapter extends RecyclerView.Adapter {
    private List<StoreData> mList;
    private AdapterDelegatesManager<List<StoreData>> mDelegatesManager;

    public StoreListAdapter(Context context, List<StoreData> list) {
        this.mList = list;

        mDelegatesManager = new AdapterDelegatesManager<>();
        mDelegatesManager.addDelegate(new StoreInfoAdapterDelegate(context));
        mDelegatesManager.addDelegate(new AdsInfoAdapterDelegate(context));
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegatesManager.getItemViewType(mList, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mDelegatesManager.onBindViewHolder(mList, position, holder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        mDelegatesManager.onBindViewHolder(mList, position, holder, payloads);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void update(List<StoreData> list) {
        mList.addAll(list);
    }
}
