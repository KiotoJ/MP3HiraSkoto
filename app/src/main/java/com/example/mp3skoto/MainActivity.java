package com.example.mp3skoto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Mp3Activity mp3 = new Mp3Activity();
        String[] listraLohatenyMp3 = mp3.getAllMp3(getAssets());
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.layout_list_view, R.id.titra_text, listraLohatenyMp3);
        final ListView listraHiraMp3 = (ListView) findViewById(R.id.listra_hira);

        listraHiraMp3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "Vonona ary...", Toast.LENGTH_SHORT).show();
            }
        });

        listraHiraMp3.setAdapter(new Mp3Adapter(this, new ArrayList<String>(Arrays.asList(listraLohatenyMp3))));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.item, menu);

        MenuItem searchMenuItem = menu.findItem( R.id.act_cherch);
        MenuItem quitMenuItem = menu.findItem( R.id.quit);
       final SearchView searchView = (SearchView) searchMenuItem.getActionView();

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quit:
                System.exit(0);
        }
        return true;
    }
}
