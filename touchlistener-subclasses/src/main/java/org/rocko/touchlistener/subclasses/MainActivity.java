package org.rocko.touchlistener.subclasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.list_view);
        String[] strs = new String[]{"ListViewAutoScroll", "RecyclerViewAutoScroll", "ScrollViewAutoScroll", "ZoomButtonsController"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ScrollViewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ZoomButtonsActivity.class));
                        break;
                }
            }
        });
    }

}
