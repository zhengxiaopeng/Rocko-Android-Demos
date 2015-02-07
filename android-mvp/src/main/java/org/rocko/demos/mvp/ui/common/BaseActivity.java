package org.rocko.demos.mvp.ui.common;

import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by Administrator on 2015/2/6.
 */
public class BaseActivity extends ActionBarActivity {

    protected  <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
