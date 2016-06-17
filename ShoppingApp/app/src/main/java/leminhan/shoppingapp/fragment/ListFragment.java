package leminhan.shoppingapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import cn.yangbingqiang.android.parallaxviewpager.ParallaxViewPager;
import leminhan.shoppingapp.R;
import leminhan.shoppingapp.ui.SlidingTabLayout;


public class ListFragment extends Fragment {

    private View view;
    int color;

    public ListFragment(int color) {
        this.color = color;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_fragment, container, false);


        return view;
    }

}

