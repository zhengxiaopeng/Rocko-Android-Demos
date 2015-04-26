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
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ScrollView;

import org.rocko.touchlistener.subclasses.widget.ScrollViewAutoScrollHelper;

/**
 * Created by Administrator on 2015/4/26.
 */
public class ScrollViewActivity extends AppCompatActivity {
    protected ScrollView scrollView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ScrollViewAutoScroll");

        init();
    }

    private void init() {
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        AutoScrollHelper autoScrollHelper = new ScrollViewAutoScrollHelper(scrollView);
        autoScrollHelper.setEnabled(true);
        scrollView.setOnTouchListener(autoScrollHelper);
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
