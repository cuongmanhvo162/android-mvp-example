package cuongvo.tinklabstest.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cuongvo.tinklabstest.BaseFragment;
import cuongvo.tinklabstest.view.fragment.CityGuideFragment;
import cuongvo.tinklabstest.view.fragment.EatFragment;
import cuongvo.tinklabstest.view.fragment.ShopFragment;

/**
 * Created by cuongvo on 6/16/17.
 * <p>
 * This is the Pager adapter for MainActivity screen.
 * Use to display
 * 1. City guide fragment
 * 2. Shop fragment
 * 3. Eat fragment
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final int NUMBERS_OF_FRAGMENT = 3;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CityGuideFragment();

        } else if (position == 1) {
            return new ShopFragment();

        } else if (position == 2) {
            return new EatFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return NUMBERS_OF_FRAGMENT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return BaseFragment.FragmentType.CityGuideFragment.getName();
            case 1:
                return BaseFragment.FragmentType.ShopFragment.getName();
            case 2:
                return BaseFragment.FragmentType.EatFragment.getName();
            default:
                return "";
        }
    }
}
