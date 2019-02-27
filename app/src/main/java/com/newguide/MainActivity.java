package com.newguide;

import android.os.Bundle;

import com.newguide.base.BaseActivity;

/**
 * @author Andevel
 */
public class MainActivity extends BaseActivity {

    private static final String GUIDE_KEY = "main_guide_pager";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //通过实现下面的两个方法就可以了

    @Override
    protected int getGuideLayout() {
        return R.layout.guide_layout;
    }

    @Override
    protected String getGuideKey() {
        return GUIDE_KEY;
    }
}
