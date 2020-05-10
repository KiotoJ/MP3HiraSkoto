package com.example.mp3skoto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
