package Objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.ufrj.nce.psa.R;

import java.util.ArrayList;

/**
 * Created by fabiofilho on 20/03/15.
 */
public class EmergencyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Emergency> mList;
    private LayoutInflater mLayoutInflater;


    public EmergencyAdapter(Context context, ArrayList<Emergency> mList){

        this.mList = mList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).getCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mLayoutInflater.inflate(R.layout.list_emergency, null);

        Button btnEmergency = (Button) convertView.findViewById(R.id.btnEmergency);
        btnEmergency.setText(mList.get(position).getName());

        return convertView;
    }
}
