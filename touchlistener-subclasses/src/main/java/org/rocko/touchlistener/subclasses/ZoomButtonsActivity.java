/*
 * Copyright 2015 Rocko(zhengxiaopeng).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rocko.touchlistener.subclasses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ZoomButtonsController;

/**
 * Created by Administrator on 2015/4/25.
 */
public class ZoomButtonsActivity extends AppCompatActivity {
    protected TextView textView;
    protected ZoomButtonsController zoomButtonsController;
    protected float zoomTextSize;
    protected ScrollView scrollView;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoombutton);
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ZoomButtonsController");
        
        inti();
    }

    private void inti() {
        textView = (TextView) findViewById(R.id.textview);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);

        zoomButtonsController = new ZoomButtonsController(scrollView);
        textView.setOnTouchListener(zoomButtonsController);
        //        zoomButtonsController.setAutoDismissed(false);
        zoomButtonsController.setZoomInEnabled(true);
        zoomButtonsController.setZoomOutEnabled(true);
        zoomButtonsController.setFocusable(true);
        zoomTextSize = textView.getTextSize();
        zoomButtonsController.setOnZoomListener(new ZoomButtonsController.OnZoomListener() {
            @Override
            public void onVisibilityChanged(boolean visible) {
            }

            @Override
            public void onZoom(boolean zoomIn) {
                if (zoomIn) {
                    zoomTextSize = zoomTextSize + 1.0f;
                } else {
                    zoomTextSize = zoomTextSize - 1.0f;
                }
                textView.setTextSize(zoomTextSize);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zoomButtonsController.setVisible(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_zoom, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_zoom:
                zoomButtonsController.setVisible(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
