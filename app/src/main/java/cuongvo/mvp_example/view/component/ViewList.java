package cuongvo.mvp_example.view.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cuongvo.mvp_example.R;
import cuongvo.mvp_example.model.data.StoreData;
import cuongvo.mvp_example.view.adapter.StoreListAdapter;
import cuongvo.mvp_example.view.util.EndlessScrollingListener;

/**
 * Created by cuongvo.
 * <p>
 * This is and custom view which serve
 * 1. Display list of data for across screen in the project.
 * 2. It can set any adapter for reuseability in across the project.
 * 3. Provide message in case no data is returned from server.
 * 4. Provide progress bar while downloading data.
 * 5. Provide load more progress bar while load more data.
 * <p>
 * The data in the list will be organized vertically with LinearLayoutManager setting.
 */
public class ViewList extends RelativeLayout {
    @BindView(R.id.view_list_list)
    RecyclerView mList;

    @BindView(R.id.view_list_message)
    TextView mMessage;

    @BindView(R.id.view_list_progress)
    ProgressBar mProgressBar;

    @BindView(R.id.load_more)
    ProgressBar mLoadMoreProgressBar;

    private boolean mIsLoadMore;
    private Context mContext;
    private StoreListAdapter mStoreListAdapter;

    private OnLoadMoreListener mLoadMoreListener;

    public ViewList(Context context) {
        super(context);

        init(context);
    }

    public ViewList(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public ViewList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = inflate(mContext, R.layout.view_list_with_progress, this);

        ButterKnife.bind(this, view);
    }

    public void displayList() {
        mList.setVisibility(View.VISIBLE);
        mMessage.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mList.addOnScrollListener(new EndlessScrollingListener((LinearLayoutManager) mList.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mLoadMoreListener.onLoadMore(page);
            }
        });
    }

    public void displayMessage(String message) {
        mList.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mMessage.setVisibility(View.VISIBLE);
        mMessage.setText(message);
    }

    public void displayProgressBar() {
        mList.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mMessage.setVisibility(View.GONE);

    }

    public void setStoreList(List<StoreData> list) {
        mStoreListAdapter = new StoreListAdapter(mContext, list);
        mList.setAdapter(mStoreListAdapter);
        mStoreListAdapter.notifyDataSetChanged();
    }

    public void updateStoreList(List<StoreData> list) {
        if (mStoreListAdapter != null) {
            mStoreListAdapter.update(list);
        }
    }

    public RecyclerView getList() {
        return this.mList;
    }

    public void setLoadMore(boolean isLoadMore) {
        this.mIsLoadMore = isLoadMore;
    }

    public boolean isLoadMore() {
        return this.mIsLoadMore;
    }

    public void displayLoadMoreProgress() {
        this.mLoadMoreProgressBar.setVisibility(View.VISIBLE);
        this.mIsLoadMore = true;
    }

    public void hideLoadMoreProgress() {
        this.mLoadMoreProgressBar.setVisibility(View.GONE);
        this.mIsLoadMore = false;
    }

    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) this.mList.getLayoutManager();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int page);
    }
}
