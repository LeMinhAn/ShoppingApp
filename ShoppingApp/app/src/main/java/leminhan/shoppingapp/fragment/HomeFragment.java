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


public class HomeFragment extends Fragment implements ObservableScrollViewCallbacks {

    private View view;
    private ParallaxViewPager mPager;
    private NavigationAdapter mPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_fragment, container, false);

        initView();

        return view;
    }

    private void initView() {
        mPagerAdapter = new NavigationAdapter(getChildFragmentManager());
        mPager = (ParallaxViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(3);

        initViewSlidingTab();

    }

    private void initViewSlidingTab() {
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.tab_indicator, R.id.text1);
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.organ));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mPager);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    private class NavigationAdapter extends CacheFragmentStatePagerAdapter {
        //Đặt tên cho các tab
        private final String[] TITLES = new String[]{getResources().getString(R.string.card_1), getResources().getString(R.string.card_2), getResources().getString(R.string.card_3),
                getResources().getString(R.string.card_4), getResources().getString(R.string.card_5), getResources().getString(R.string.card_6), getResources().getString(R.string.card_7),
                getResources().getString(R.string.card_8), getResources().getString(R.string.card_9), getResources().getString(R.string.card_10)};

        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Fragment createItem(int position) {
            Fragment fragment;
            final int pattern = position % 11;
            switch (pattern) {
                case 0:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
                case 1:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
                case 2:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
                case 3:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
                case 4:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
                case 5:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
                case 6:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
                case 7:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
                case 8:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
                case 9:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
                default:
                    fragment = new ListFragment(getActivity().getResources().getColor(R.color.white));
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }

}

