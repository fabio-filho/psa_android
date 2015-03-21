package com.ufrj.nce.psa.Objects;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by fabiofilho on 3/12/15.
 */
public class MyTextView {

    private TextView textView;

    private Context context;


    public MyTextView(Context context, TextView textView) {

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),
                "fonts/"+ Fonts.getDefaultFont());
        textView.setTypeface(custom_font);

        this.context = context;
        this.textView = textView;
    }

    public MyTextView(Context context, TextView textView, String font) {

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),
                "fonts/" +font);
        textView.setTypeface(custom_font);

        this.context = context;
        this.textView = textView;
    }





    public TextView getTextView(){
        return textView;
    }


}
