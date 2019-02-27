package com.newguide.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.newguide.guide.GuideFragment;

/**
 * @author Andevel
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showGuideDialog();
    }

    /**
     * 显示引导页，可以根据具体的业务需求进行显示控制
     */
    protected void showGuideDialog(){
        if (getGuideLayout() != -1 && !TextUtils.isEmpty(getGuideKey())) {
            GuideFragment.show(this, getGuideLayout(), getGuideKey());
        }
    }


    /**
     * override this function for show guide view, see to {@link #getGuideKey()}.
     */
    protected int getGuideLayout() {
        return -1;
    }

    /**
     * override this function for show guide view, see to {@link #getGuideKey()}.
     */
    protected String getGuideKey() {
        return "";
    }
}
