package leminhan.shoppingapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;

import java.util.ArrayList;

import leminhan.shoppingapp.R;
import leminhan.shoppingapp.model.CardItem;
import leminhan.shoppingapp.model.DataCardItem;
import leminhan.shoppingapp.ui.ViewHolderComment;
import leminhan.shoppingapp.utils.Constants;


public class CommentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CardItem> cardItems;
    private DisplayImageOptions options;
    private ViewHolderComment viewHolderComment;

    public CommentAdapter(Context context, ArrayList<CardItem> cardItems, DisplayImageOptions options) {
        this.context = context;
        this.cardItems = cardItems;
        this.options = options;
    }

    @Override
    public int getCount() {
        return cardItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cardItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.item_comment, null);
            viewHolderComment = new ViewHolderComment(convertView, context, Constants.SCALE_IMAGE_CARD.AVATAR_COMMENT);
            convertView.setTag(viewHolderComment);
        } else {
            viewHolderComment = (ViewHolderComment) convertView.getTag();
        }
        DataCardItem item = new DataCardItem();
        try {
            item.valueOf(cardItems.get(position).getCard_data().getJSONObject(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewHolderComment.getTvIntroComment().setText(item.getContent());
        viewHolderComment.getTvNameUserComment().setText(item.getName());
        viewHolderComment.getTvTimeUserComment().setText(item.getDate());
        viewHolderComment.getRbRatingComment().setRating(item.getRate());
        ImageLoader.getInstance().displayImage(item.getIcon_image(), viewHolderComment.getIvAvatarComment(), options);
        return convertView;
    }
}
