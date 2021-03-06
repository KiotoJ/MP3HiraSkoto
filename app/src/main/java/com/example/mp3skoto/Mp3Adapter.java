package com.example.mp3skoto;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class Mp3Adapter extends BaseAdapter implements ListAdapter, Filterable {
    private ArrayList<String> origListraLohatenyMp3 = new ArrayList<String>();
    private ArrayList<String> listraLohatenyMp3 = new ArrayList<String>();
    private Context context;
    private MediaPlayerService player;
    boolean serviceBound = false;
    private ItemFilter mFilter = new ItemFilter();

    public Mp3Adapter(Context context, ArrayList<String> listraLohatenyMp3) {
        this.listraLohatenyMp3 = listraLohatenyMp3;
        this.context = context;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final ArrayList<String> list = listraLohatenyMp3;

            int count = list.size();
            final ArrayList<String> nlist = new ArrayList<String>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            origListraLohatenyMp3 = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }

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

    /*private void playAudio(String media) {
        //Check is service is active
        if (!serviceBound) {
            Intent playerIntent = new Intent(context, MediaPlayerService.class);
            playerIntent.putExtra("media", media);
            context.startService(playerIntent);
            context.bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else {
            //Service is active
            //Send media with BroadcastReceiver
        }
    }*/

    //Binding this Client to the AudioPlayer Service
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) service;
            player = binder.getService();
            serviceBound = true;

            Toast.makeText(context, "Service Bound", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_list_view, null);

            final TextView anaranaHiraIray = (TextView) view.findViewById(R.id.titra_text);
            anaranaHiraIray.setText(this.listraLohatenyMp3.get(position));

            final ImageButton btn_play = (ImageButton) view.findViewById(R.id.alefa_hira);
            btn_play.setImageResource(R.drawable.play);
            btn_play.setTag(position);
            final Mp3Activity mp3 = new Mp3Activity();

            btn_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = (Integer)v.getTag();
                    String hiraVoatsindry = anaranaHiraIray.getText().toString();

                    mp3.playMp3(context.getAssets(), hiraVoatsindry, btn_play);
                }
            });
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }
}
