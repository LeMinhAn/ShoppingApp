package leminhan.shoppingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import org.json.JSONException;

import leminhan.shoppingapp.R;
import leminhan.shoppingapp.activity.ReadMoreActivity;
import leminhan.shoppingapp.adapter.AdapterRowVerticalCardSuggest;
import leminhan.shoppingapp.adapter.RingToneAdapter;
import leminhan.shoppingapp.adapter.SlideImageAdapter;
import leminhan.shoppingapp.model.CardItem;
import leminhan.shoppingapp.model.DataCardItem;
import leminhan.shoppingapp.utils.Constants;
import leminhan.shoppingapp.utils.UIUtils;
import leminhan.shoppingapp.utils.Utils;

/**
 * Created by tobrother on 28/12/2015.
 */
//set Adapter cho phần dưới của App, Film, Game, Book, wallpaper, ringtone trong phần nổi bật, top, new
public class ViewHolderListVerticalCard extends RecyclerView.ViewHolder {
    private Button btReadMoreListVerticalCard;
    public ListView lvListVerticalCard;
    private TextView tvTitleListVerticalCard;
    private boolean hideActionMore = false;

    public ViewHolderListVerticalCard(View v) {
        super(v);
        btReadMoreListVerticalCard = (Button) v.findViewById(R.id.btReadMoreListVerticalCard);
        lvListVerticalCard = (ListView) v.findViewById(R.id.lvListVerticalCard);
        tvTitleListVerticalCard = (TextView) v.findViewById(R.id.tvTitleListVerticalCard);
    }

    public void setValue(final CardItem cardItem, final Context context, DisplayImageOptions options) {
        int cardDataType = cardItem.getCard_data_type();
        switch (cardDataType) {
            case Constants.CARD_DATA_TYPE.APP:
            case Constants.CARD_DATA_TYPE.FILM:
            case Constants.CARD_DATA_TYPE.GAME:
            case Constants.CARD_DATA_TYPE.BOOK:
                AdapterRowVerticalCardSuggest myAdapterRowVerticalCardSuggest = new AdapterRowVerticalCardSuggest(context, cardItem.getCard_data(), (float) 4, cardItem.getCard_data_type());
                getLVListVerticalCard().setAdapter(myAdapterRowVerticalCardSuggest);
                break;
            case Constants.CARD_DATA_TYPE.WALLPAPER:
                SlideImageAdapter mySlideImageAdapter = new SlideImageAdapter(cardItem.getCard_data(), context, options, cardItem.getCard_data_type());
                getLVListVerticalCard().setAdapter(mySlideImageAdapter);
                break;
            case Constants.CARD_DATA_TYPE.RINGTONE:
                RingToneAdapter myRingToneAdapter = new RingToneAdapter(cardItem.getCard_data(), context, options, cardItem.getCard_data_type());
                getLVListVerticalCard().setAdapter(myRingToneAdapter);
                break;
        }
        //Sự kiện click cho từng items phía dưới trong phần nổi bật, top, new
        getLVListVerticalCard().setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int mposition, long id) {

                try {
                    Utils.EventClick.CardClick(context, DataCardItem.get(cardItem.getCard_data().getJSONObject(mposition), cardItem.getCard_data_type()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        UIUtils.setListViewHeightBasedOnChildren(getLVListVerticalCard());
        getTvTitleListVerticalCard().setText(cardItem.getCard_title());
        getBtReadMoreListVerticalCard().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadMoreActivity.class);
                intent.putExtra(Constants.Intent.READ_MORE_TYPE, 0);
                context.startActivity(intent);
            }
        });
    }

    public boolean isHideActionMore() {
        return hideActionMore;
    }

    public void setHideActionMore(boolean hideActionMore) {
        this.hideActionMore = hideActionMore;
    }

    public Button getBtReadMoreListVerticalCard() {
        return btReadMoreListVerticalCard;
    }

    public void setBtReadMoreListVerticalCard(Button btReadMoreListVerticalCard) {
        this.btReadMoreListVerticalCard = btReadMoreListVerticalCard;
    }
    //

    public ListView getLVListVerticalCard() {
        return lvListVerticalCard;
    }

    public void setLVListVerticalCard(ListView lvListVerticalCard) {
        this.lvListVerticalCard = lvListVerticalCard;
    }

    //
    public TextView getTvTitleListVerticalCard() {
        return tvTitleListVerticalCard;
    }

    public void setTvTitleListVerticalCard(TextView tvTitleListVerticalCard) {
        this.tvTitleListVerticalCard = tvTitleListVerticalCard;
    }
}
