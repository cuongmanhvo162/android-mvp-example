package cuongvo.tinklabstest;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import cuongvo.tinklabstest.view.adapter.MainPagerAdapter;
import io.karim.MaterialTabs;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_activity_pager)
    ViewPager mViewPager;

    @BindView(R.id.main_activity_tabs)
    MaterialTabs mTabs;

    public MainActivity() {
        mActivityType = ActivityType.MainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainPagerAdapter);

        mTabs.setViewPager(mViewPager);
    }
}
