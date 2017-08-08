package cuongvo.mvp_example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by cuongvo on 7/21/17.
 * <p>
 * This is base class for every activities in the project.
 * It use to construct a activity with essential information.
 * Also, it contains necessary methods such as open fragment(with backstack or not).
 */
public class BaseActivity extends AppCompatActivity {

    public enum ActivityType {
        MainActivity(1, R.layout.activity_main, "MainActivity");

        private int id;
        private int layoutId;
        private String name;

        ActivityType(int id, int layoutId, String name) {
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

    protected ActivityType mActivityType;
    protected View mView;
    protected BaseFragment.FragmentType mFragmentType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mActivityType.getLayoutId());
        mView = getLayoutInflater().inflate(mActivityType.getLayoutId(), null);

    }

    public void openFragment(Fragment baseFragment, int contentLayoutId, BaseFragment.FragmentType fragmentType) {
        mFragmentType = fragmentType;

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(contentLayoutId, baseFragment, fragmentType.getName());
        fragmentTransaction.commit();

    }

    public void openFragmentAndClearBackStack(Fragment baseFragment, int contentLayoutId, BaseFragment.FragmentType fragmentType) {
        mFragmentType = fragmentType;

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStackImmediate();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(contentLayoutId, baseFragment, fragmentType.getName());
        fragmentTransaction.commit();

    }

    public void openFragmentWithCommitStateLoss(Fragment baseFragment, int contentLayoutId, BaseFragment.FragmentType fragmentType) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(contentLayoutId, baseFragment, fragmentType.getName());
        fragmentTransaction.addToBackStack(baseFragment.getClass().getName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void replaceFragment(Fragment fragment, int contentLayoutId, BaseFragment.FragmentType fragmentType) {
        mFragmentType = fragmentType;
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        //fragment not in back stack, createForSuccessTopup it.
        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(contentLayoutId, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    protected void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

    }
}
