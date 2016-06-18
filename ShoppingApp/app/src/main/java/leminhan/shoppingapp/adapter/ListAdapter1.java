package leminhan.shoppingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import leminhan.shoppingapp.R;

public class ListAdapter1 extends BaseAdapter {
    private Context mContext;
    private final String[] web1;
    private final int[] Imageid1;


    public ListAdapter1(Context c, String[] web, int[] Imageid ) {
        mContext = c;
        this.Imageid1 = Imageid;
        this.web1 = web;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web1.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.items_card, null);
            TextView textView = (TextView) grid.findViewById(R.id.tv_gridview);
            ImageView imageView = (ImageView)grid.findViewById(R.id.iv_gridview);
            textView.setText(web1[position]);
            imageView.setImageResource(Imageid1[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}