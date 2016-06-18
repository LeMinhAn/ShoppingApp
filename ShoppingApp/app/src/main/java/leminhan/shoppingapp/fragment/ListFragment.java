package leminhan.shoppingapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.GridView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import leminhan.shoppingapp.MainActivity;
import leminhan.shoppingapp.R;
import leminhan.shoppingapp.adapter.ListAdapter;
import leminhan.shoppingapp.adapter.ListAdapter1;


public class ListFragment extends Fragment implements ObservableScrollViewCallbacks {
    private ObservableScrollView svParentHomeFragment;
    private View view;
    int color;
    private GridView gv_quangcao3_card1, gv_quangcao4_card1;
    String[] web = {
            "Google",
            "Github",
            "Instagram",
            "Facebook",
            "Flickr",
            "Pinterest",

    };
    int[] imageId = {
            R.drawable.background_gridview_test,
            R.drawable.background_gridview_test,
            R.drawable.background_gridview_test,
            R.drawable.background_gridview_test,
            R.drawable.background_gridview_test,
            R.drawable.background_gridview_test
    };
    String[] web1 = {
            "Google",
            "Github",
            "Instagram",
            "Facebook",
            "Flickr",
            "Pinterest",

    };
    int[] imageId1 = {
            R.drawable.background_gridview_test,
            R.drawable.background_gridview_test,
            R.drawable.background_gridview_test,
            R.drawable.background_gridview_test,
            R.drawable.background_gridview_test,
            R.drawable.background_gridview_test
    };


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

        initView();
        initActions();

        return view;
    }

    private void initActions() {
    }

    private void initView() {
        svParentHomeFragment = (ObservableScrollView) view.findViewById(R.id.svParentHomeFragment);
        svParentHomeFragment.setScrollViewCallbacks(this);
        ListAdapter listAdapter = new ListAdapter(getActivity(), web, imageId);
        ListAdapter1 listAdapter1 = new ListAdapter1(getActivity(), web1, imageId1);
        gv_quangcao3_card1 = (GridView) view.findViewById(R.id.gv_quangcao3_card1);
        gv_quangcao3_card1.setAdapter(listAdapter);
        gv_quangcao4_card1 = (GridView) view.findViewById(R.id.gv_quangcao4_card1);
        gv_quangcao4_card1.setAdapter(listAdapter1);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
//        if (((AppCompatActivity) getActivity()).getSupportActionBar() == null) {
//            return;
//        }
//        if (scrollState == ScrollState.UP) {
//            if (((AppCompatActivity) getActivity()).getSupportActionBar().isShowing()) {
//                MainActivity.toolbar.animate()
//                        .translationY(-getActivity().findViewById(R.id.toolbar).getBottom())
//                        .setInterpolator(new AccelerateInterpolator())
//                        .start();
//                ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
//            }
//        } else if (scrollState == ScrollState.DOWN) {
//            if (!((AppCompatActivity) getActivity()).getSupportActionBar().isShowing()) {
//                ((AppCompatActivity) getActivity()).getSupportActionBar().show();
//                MainActivity.toolbar.animate()
//                        .translationY(0)
//                        .setInterpolator(new AccelerateInterpolator())
//                        .start();
//            }
//        }
    }
}


