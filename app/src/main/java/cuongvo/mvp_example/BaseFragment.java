package cuongvo.mvp_example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cuongvo on 7/21/17.
 * <p>
 * The base class use to construct essential information and properties for any fragment in the project.
 */
public class BaseFragment extends Fragment {

    /**
     * The enum contains all of fragments's information in the app.
     * With it, we can access fragment layout and fragment name easily.
     * Also provide a quick way to create a new fragment.
     */
    public enum FragmentType {
        CityGuideFragment(1, R.layout.fragment_city_guide, "City Guide"),
        ShopFragment(2, R.layout.fragment_shop, "Shop"),
        EatFragment(3, R.layout.fragment_eat, "Eat");

        private int id;
        private int layoutId;
        private String name;

        FragmentType(int id, int layoutId, String name) {
            this.id = id;
            this.layoutId = layoutId;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLayoutId() {
            return layoutId;
        }

        public void setLayoutId(int layoutId) {
            this.layoutId = layoutId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    protected AppCompatActivity mContext;
    protected FragmentType mFragmentType;
    protected View mContainer;

    public BaseFragment() {
    }

    @SuppressLint("ValidFragment")
    public BaseFragment(Context context, FragmentType fragmentType) {
        this.mContext = (AppCompatActivity) context;
        this.mFragmentType = fragmentType;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (AppCompatActivity) context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainer = inflater.inflate(mFragmentType.getLayoutId(), null);
        return mContainer;
    }

}
