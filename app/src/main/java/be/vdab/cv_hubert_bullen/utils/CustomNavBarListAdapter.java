package be.vdab.cv_hubert_bullen.utils;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import be.vdab.cv_hubert_bullen.R;

public class CustomNavBarListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public CustomNavBarListAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.navbarlist_layout, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.navbarlist_layout, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.nav_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.nav_icon);

        txtTitle.setText(itemname[position]);

        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(),imgid[position],null);
        drawable.setColorFilter(context.getResources().getColor(R.color.lightBlack), PorterDuff.Mode.MULTIPLY);
        imageView.setImageDrawable(drawable);


        return rowView;

    };
}
