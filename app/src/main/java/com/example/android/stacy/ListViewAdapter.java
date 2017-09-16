package com.example.android.stacy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;


public class ListViewAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<getData> DataList;
    ImageLoader imageLoader = Controller.getInstance().getImageLoader();

    public ListViewAdapter(Activity activity, List<getData> dataitem) {
        this.activity = activity;
        this.DataList = dataitem;
    }

    @Override
    public int getCount() {
        return DataList.size();
    }

    @Override
    public Object getItem(int location) {
        return DataList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_view_row, null);

        if (imageLoader == null)
            imageLoader = Controller.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView _id = (TextView) convertView.findViewById(R.id._id);
        TextView url = (TextView) convertView.findViewById(R.id.git_hub_url);
        getData m = DataList.get(position);
        thumbNail.setImageUrl(m.getImage(), imageLoader);
        name.setText(m.getName());
        _id.setText(String.valueOf(m.get_Id()));
        url.setText(m.gethtml());

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                getData m = DataList.get(position);
                Intent intent = new Intent(activity, DisplayUser.class);
                intent.putExtra("login", m.getName());
                intent.putExtra("id",String.valueOf(m.get_Id()));
                intent.putExtra( "avatar_url",m.getImage());
                intent.putExtra("url", m.gethtml());
                // Start SingleItemView Class
                activity.startActivity(intent);

            }
        });

        return convertView;
    }


}
