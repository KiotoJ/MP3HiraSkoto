package com.example.mp3skoto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Mp3Activity mp3 = new Mp3Activity();
        String[] listraLohatenyMp3 = mp3.getAllMp3(getAssets());
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listraLohatenyMp3);
        final ListView listraHiraMp3 = (ListView) findViewById(R.id.listra_hira);

        listraHiraMp3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Henoy ary...", Toast.LENGTH_SHORT).show();
                String hiraVoatsindry = (String) listraHiraMp3.getItemAtPosition(position);
                mp3.playMp3(getAssets(), hiraVoatsindry);
            }
        });

        listraHiraMp3.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.item, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.act_cherch);
       final SearchView searchView = (SearchView) myActionMenuItem.getActionView();

       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {

               return true;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               Toast.makeText(getApplicationContext(), "Recherche", Toast.LENGTH_SHORT).show();
               return true;
           }
       });
        return true;
    }
}
