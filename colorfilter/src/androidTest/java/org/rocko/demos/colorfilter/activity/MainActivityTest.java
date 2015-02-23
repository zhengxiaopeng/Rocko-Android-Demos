package org.rocko.demos.colorfilter.activity;

import android.test.ActivityInstrumentationTestCase2;

import org.rocko.demos.colorfilter.MainActivity;

/**
 * Created by Rocko on 2015/2/17 16:10.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity().getName();
    }

    public void testUI() throws Exception {
    }
}
