package leminhan.shoppingapp.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import leminhan.shoppingapp.R;
import leminhan.shoppingapp.animation.TransitionHelper;
import leminhan.shoppingapp.fragment.DataCardParentFragment2;
import leminhan.shoppingapp.fragment.HistorySettingFragment;
import leminhan.shoppingapp.fragment.SettingFragment;
import leminhan.shoppingapp.utils.Constants;

public class HomeActivity extends AppCompatActivity {

    private AccountHeader headerResult;
    private SearchView searchView = null;
    private ImageView iv_toolbar;
    public static TextView tvToolBarTitle;
    boolean doubleBackToExitPressedOnce;
    public static LinearLayout llActionBar;
    public static Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupWindowAnimations();
        initViews();
        initHeaderAccount();
        initMenuDrawer(savedInstanceState);
        initFragment();
        initAction();

    }

    @SuppressWarnings("unchecked")
    void transitionTo(Intent i) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    private void initAction() {

        iv_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, GioHangActivity.class);
                transitionTo(i);
            }
        });
    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.APP);
        tvToolBarTitle.setTextColor(getResources().getColor(R.color.text_toolbar_change));
        transaction.replace(R.id.flMainContainer, fragment);
        transaction.commit();
    }

    public void initViews() {
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        llActionBar = (LinearLayout) findViewById(R.id.llActionBar);
        tvToolBarTitle = (TextView) findViewById(R.id.tvToolBarTitle);
        iv_toolbar = (ImageView) findViewById(R.id.iv_toolbar);
    }

    public void initHeaderAccount() {
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(getResources().getString(R.string.account_name)).withEmail(getResources().getString(R.string.account_mail)).withIcon(getResources().getDrawable(R.drawable.icon_wallpaper))
                )
                //thay đổi account
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
    }

    public void initMenuDrawer(Bundle savedInstanceState) {
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(myToolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.menu_home).withIcon(FontAwesome.Icon.faw_home),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.menu_application).withIcon(FontAwesome.Icon.faw_cubes),
                        new SecondaryDrawerItem().withName(R.string.menu_game).withIcon(FontAwesome.Icon.faw_gamepad),
                        new SecondaryDrawerItem().withName(R.string.menu_ebook).withIcon(FontAwesome.Icon.faw_book),
                        new SecondaryDrawerItem().withName(R.string.menu_movie).withIcon(FontAwesome.Icon.faw_video_camera),
                        new SecondaryDrawerItem().withName(R.string.menu_walpaper).withIcon(FontAwesome.Icon.faw_picture_o),
                        new SecondaryDrawerItem().withName(R.string.menu_ringstone).withIcon(FontAwesome.Icon.faw_music),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.menu_check).withIcon(FontAwesome.Icon.faw_star),
                        new SecondaryDrawerItem().withName(R.string.menu_history).withIcon(FontAwesome.Icon.faw_history),
                        new SecondaryDrawerItem().withName(R.string.menu_contact).withIcon(FontAwesome.Icon.faw_phone)
                )
                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View clickedView) {
                        return true;
                    }
                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        getSupportActionBar().show();
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .addStickyDrawerItems(
                        new SecondaryDrawerItem().withName(R.string.menu_setting).withIcon(FontAwesome.Icon.faw_cog).withIdentifier(10)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        Fragment fragment = null;
                        switch (position) {
                            case 1:
//                                fragment = new HomeFragment();
                                fragment = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.APP);
                                tvToolBarTitle.setTextColor(getResources().getColor(R.color.text_toolbar_change));
                                break;
                            case 3:
                                fragment = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.APP);
                                tvToolBarTitle.setTextColor(getResources().getColor(R.color.text_toolbar_change));
                                break;
                            case 4:
                                fragment = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.GAME);
                                tvToolBarTitle.setTextColor(getResources().getColor(R.color.text_toolbar_change));
                                break;
                            case 5:
                                fragment = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.BOOK);
                                tvToolBarTitle.setTextColor(getResources().getColor(R.color.text_toolbar_change));
                                break;
                            case 6:
                                fragment = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.FILM);
                                tvToolBarTitle.setTextColor(getResources().getColor(R.color.text_toolbar_change));
                                break;
                            case 7:
                                fragment = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.WALLPAPER);
                                tvToolBarTitle.setTextColor(getResources().getColor(R.color.text_toolbar_change));
                                break;
                            case 8:
                                fragment = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.RINGTONE);
                                tvToolBarTitle.setTextColor(getResources().getColor(R.color.text_toolbar_change));
                                break;
                            case 10:
                                fragment = new SettingFragment();
                                break;
                            case 11:
                                fragment = new HistorySettingFragment();
                                break;
                            case 12:
                                fragment = new SettingFragment();
                                break;
                            case -1:
                                fragment = new SettingFragment();
                                break;
                        }
                        transaction.replace(R.id.flMainContainer, fragment);
                        transaction.commit();
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = new MenuInflater(getApplicationContext());
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fm = null;
        switch (item.getItemId()) {
            case R.id.trangchu:
                fm = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.APP);
                break;
            case R.id.ungdung:
                fm = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.APP);
                break;
            case R.id.trochoi:
                fm = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.GAME);
                break;
            case R.id.ebook:
                fm = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.BOOK);
                break;
            case R.id.phim:
                fm = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.FILM);
                //Xử lý xem danh sách sinh viên
                break;
            case R.id.hinhnen:
                fm = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.WALLPAPER);
                //xử lý xem thông tin lớp DHTH1A
                break;
            case R.id.nhacchuong:
                fm = new DataCardParentFragment2().newInstance(Constants.CARD_DATA_TYPE.RINGTONE);
                break;
            case R.id.danhdau:
                fm = new SettingFragment();
                break;
            case R.id.lichsucaidat:
                fm = new HistorySettingFragment();
                break;
            case R.id.lienhe:
                fm = new SettingFragment();
                break;
            case R.id.caidat:
                fm = new SettingFragment();
                break;
        }
        transaction.replace(R.id.flMainContainer, fm);
        transaction.commit();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, R.string.back_again, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            //super.onBackPressed();
            finish();
            return;
        }
    }
}
