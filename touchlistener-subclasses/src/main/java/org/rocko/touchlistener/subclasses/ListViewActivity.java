package org.rocko.touchlistener.subclasses;

import android.os.Bundle;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/25.
 */
public class ListViewActivity extends AppCompatActivity {
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ListViewAutoScroll");
        
        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.list_view);

        String[] strs = getData(100);
        List<Map<String, Object>> list = new ArrayList<>();
        for (String str : strs) {
            Map<String, Object> map = new HashMap<>();
            map.put("text", str);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.item_simple, new String[]{"text"}, new int[]{R.id.text_view});
        listView.setAdapter(adapter);

        AutoScrollHelper autoScrollHelper = new ListViewAutoScrollHelper(listView);
        listView.setOnTouchListener(autoScrollHelper);
        autoScrollHelper.setEnabled(true);
//        autoScrollHelper.setActivationDelay(3000);
//        autoScrollHelper.setRampDownDuration(3000);
        Toast.makeText(this, "长按上或下边缘", Toast.LENGTH_SHORT).show();
    }

    private String[] getData(int size) {
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = "item " + i;
        }
        return result;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
