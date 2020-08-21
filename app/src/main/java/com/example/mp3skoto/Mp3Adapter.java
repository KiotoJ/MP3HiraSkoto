package com.example.mp3skoto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class Mp3Adapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> listraLohatenyMp3 = new ArrayList<String>();
    private Context context;

    public Mp3Adapter(Context context, ArrayList<String> listraLohatenyMp3) {
        this.listraLohatenyMp3 = listraLohatenyMp3;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listraLohatenyMp3.size();
    }

    @Override
    public Object getItem(int position) {
        return listraLohatenyMp3.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;//return this.listraLohatenyMp3.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_list_view, null);

            final TextView anaranaHiraIray = (TextView) view.findViewById(R.id.titra_text);
            anaranaHiraIray.setText(this.listraLohatenyMp3.get(position));

            Button btn_play = (Button) view.findViewById(R.id.alefa_hira);
            btn_play.setTag(position);
            final Mp3Activity mp3 = new Mp3Activity();

            btn_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = (Integer)v.getTag();
                    String hiraVoatsindry = anaranaHiraIray.getText().toString();
                    Toast.makeText(context, "Henoy ary..."+hiraVoatsindry, Toast.LENGTH_SHORT).show();
                    mp3.playMp3(context.getAssets(), hiraVoatsindry);
                }
            });
        }
        return view;
    }
}
