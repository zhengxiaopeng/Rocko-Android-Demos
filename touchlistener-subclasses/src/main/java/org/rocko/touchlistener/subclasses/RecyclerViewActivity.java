package org.rocko.touchlistener.subclasses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import org.rocko.touchlistener.subclasses.adapter.SimpleRecylerViewAdapter;
import org.rocko.touchlistener.subclasses.widget.RecyclerViewAutoScrollHelper;

/**
 * Created by Administrator on 2015/4/25.
 */
public class RecyclerViewActivity extends AppCompatActivity {
    protected RecyclerView recyclerView;
    protected LinearLayoutManager linearLayoutManager;
    protected StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylerview);
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("RecyclerViewAutoScroll");

        
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new SimpleRecylerViewAdapter(this, getData(100)));

        AutoScrollHelper autoScrollHelper = new RecyclerViewAutoScrollHelper(recyclerView);
        autoScrollHelper.setEnabled(true);
        recyclerView.setOnTouchListener(autoScrollHelper);
    }

    private String[] getData(int size) {
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = "item " + i;
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_linearLayout_manager_HORIZONTAL:
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                break;
            case R.id.action_linearLayout_manager_VERTICAL:
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                break;
            case R.id.action_staggered_gridlayout_manager_VERTICAL:
                staggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            case R.id.action_staggered_gridlayout_manager_HORIZONTAL:
                staggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
