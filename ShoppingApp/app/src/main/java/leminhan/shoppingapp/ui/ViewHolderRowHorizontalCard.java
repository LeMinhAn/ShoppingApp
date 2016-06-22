package leminhan.shoppingapp.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import org.json.JSONObject;

import leminhan.shoppingapp.R;
import leminhan.shoppingapp.model.DataCardItem;
import leminhan.shoppingapp.utils.Constants;
import leminhan.shoppingapp.utils.InstallChecker;
import leminhan.shoppingapp.utils.SizeCalculator;
import leminhan.shoppingapp.utils.UIUtils;
import leminhan.shoppingapp.utils.Utils;

import static leminhan.shoppingapp.utils.UIUtils.shortString;

/**
 * Created by tobrother on 29/12/2015.
 */
public class ViewHolderRowHorizontalCard extends RecyclerView.ViewHolder {
    private ImageView ivImageCardSuggest2;
    private ShortActionButton btActionHorizontal;
    private FrameLayout flMenuCard2Container;
    private int imageSize;
    private Handler mHandler = new Handler();
    private CardView cvContainerColHorizontalItem;
    private DisplayImageOptions options;

    public ViewHolderRowHorizontalCard(View v) {
        super(v);
        ivImageCardSuggest2 = (ImageView) v.findViewById(R.id.ivImageCardSuggest2);
        btActionHorizontal = (ShortActionButton) v.findViewById(R.id.btActionHorizontal);
        flMenuCard2Container = (FrameLayout) v.findViewById(R.id.flMenuCard2Container);
        cvContainerColHorizontalItem = (CardView) v.findViewById(R.id.cvContainerColHorizontalItem);
        // get calculator size
        int paddingSize = SizeCalculator.getManager().paddingSize;
        int pbWidth = SizeCalculator.getManager().pbWidthHalf3;
        LinearLayout.LayoutParams lpIconImage = SizeCalculator.getManager().lpIconImageHalf3;
        RelativeLayout.LayoutParams lpActionButton = SizeCalculator.getManager().lpActionButtonHalf3;
        RelativeLayout.LayoutParams lpLayOutProgress = SizeCalculator.getManager().lpLayOutProgressHalf3;
        RelativeLayout.LayoutParams lpProgress = SizeCalculator.getManager().lpProgressHalf3;
        RelativeLayout.LayoutParams lpSetting = SizeCalculator.getManager().lpLayOutSettingHalf3;
        ivImageCardSuggest2.setLayoutParams(lpIconImage);
        btActionHorizontal.setLayoutParams(lpActionButton);
        //
        btActionHorizontal.rlProcessAppManage.setLayoutParams(lpLayOutProgress);
        btActionHorizontal.rlProcessAppManage.setPadding(paddingSize, paddingSize, paddingSize, paddingSize);
        //
        btActionHorizontal.cpcProcessAppManage.setLayoutParams(lpProgress);
        btActionHorizontal.cpcProcessAppManage.setProgressBarWidth(pbWidth);
        btActionHorizontal.cpcProcessAppManage.setBackgroundProgressBarWidth(pbWidth);
        //
        btActionHorizontal.rlSetting.setLayoutParams(lpSetting);
        btActionHorizontal.rlSetting.setPadding(paddingSize * 2, paddingSize, paddingSize * 2, paddingSize);

        options = Constants.options;
    }

    public void setValue(final Context context, JSONObject jsonObject, int cardDataType) {
        final DataCardItem dataCardItem = DataCardItem.get(jsonObject, cardDataType);
        dataCardItem.addUpdateListener(this.mUpdateListener);
        btActionHorizontal.rebind(dataCardItem);
        //
        btActionHorizontal.rbRatingCardSuggest.setScore(dataCardItem.getRate());
        btActionHorizontal.tvNameCardSuggest2.setText(shortString(dataCardItem.getName(), 10)); // shortString  rút gọn tên của app

        UIUtils.loadImageLoader(Constants.options, dataCardItem.getIcon_image(), ivImageCardSuggest2);
        //setImageViewWithScale(ivImageCardSuggest2, SizeCalculator.getManager().imageSizeHalf3, SizeCalculator.getManager().imageSizeHalf3, dataCardItem.getIcon_image());
        //
        final DataCardItem dataItem = dataCardItem;
        getFlMenuCard2Container().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, v);
                popup.getMenuInflater().inflate(R.menu.menu_card, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.install:
                                if (!Utils.Network.isNetWorkConnected(context)) {
                                    Toast.makeText(context, context.getString(R.string.network_unavaliable), Toast.LENGTH_LONG).show();
                                } else {
                                    InstallChecker.checkAndInstall(dataItem, (Activity) context);
                                }
                                break;
                            case R.id.add_favorite:
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        cvContainerColHorizontalItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.EventClick.CardClick(context, dataCardItem);
            }
        });

    }

    public ImageView getIvImageCardSuggest2() {
        return ivImageCardSuggest2;
    }

    public void setIvImageCardSuggest2(ImageView ivImageCardSuggest2) {
        this.ivImageCardSuggest2 = ivImageCardSuggest2;
    }

    public FrameLayout getFlMenuCard2Container() {
        return flMenuCard2Container;
    }

    public void setFlMenuCard2Container(FrameLayout flMenuCard2Container) {
        this.flMenuCard2Container = flMenuCard2Container;
    }

    public ShortActionButton getBtActionHorizontal() {
        return btActionHorizontal;
    }

    public void setBtActionHorizontal(ShortActionButton btActionHorizontal) {
        this.btActionHorizontal = btActionHorizontal;
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    private DataCardItem.AppInfoUpdateListener mUpdateListener = new DataCardItem.AppInfoUpdateListener() {
        public void onContentUpdate(final DataCardItem appinfo) {
            ViewHolderRowHorizontalCard.this.mHandler.post(new Runnable() {
                public void run() {
                }
            });
        }

        public void onStatusUpdate(final DataCardItem appinfo) {
            ViewHolderRowHorizontalCard.this.mHandler.post(new Runnable() {
                public void run() {
                    getBtActionHorizontal().rebind(appinfo);
                }
            });
        }
    };

}
