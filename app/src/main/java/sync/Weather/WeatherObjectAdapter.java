package sync.Weather;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.MessageFormat;
import java.util.ArrayList;

import sync.evidentmobile.R;

/**
 * Created by rbrow_000 on 9/21/2017.
 */
public class WeatherObjectAdapter extends ArrayAdapter<String> {

    /**
     * Adapter context
     */
    Context mContext;
    ArrayList<String> mArray;
    public WeatherObjectAdapter(Context context, int resource, ArrayList<String> _array) {
        super(context, resource);

        this.mArray = _array;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if(mArray.size() == 0){
            return 0;
        }else{
            return mArray.size();
        }
    }

    /**
     * Returns the view for a specific item on the grid view
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String item = mArray.get(position);
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.listview_item, parent, false);

        ((TextView)rootView.findViewById(R.id.textViewListViewItem)).setText(item);


        return rootView;
    }

    @Override
    public void notifyDataSetChanged() {
        // TODO Auto-generated method stub
        if(getCount() == 0){
            //show layout or something that notifies that no list is in..
        }else{
            // this is to make sure that you can call notifyDataSetChanged in any place and any thread
            new Handler(getContext().getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    WeatherObjectAdapter.super.notifyDataSetChanged();
                }
            });
        }
    }
}
