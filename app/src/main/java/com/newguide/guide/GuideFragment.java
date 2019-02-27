package com.newguide.guide;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;

import com.newguide.Constant;
import com.newguide.R;
import com.newguide.util.SPUtils;
import com.newguide.base.BaseDialogFragment;

/**
 * Description: 引导页类
 * @author Andevel
 */

public class GuideFragment extends BaseDialogFragment implements View.OnClickListener {

    private static final String GUIDE_PROP = "Guide.prop";

    protected static Set<String> sHasShowSet = new HashSet<>();

    protected static String mKey;

    @BindView(R.id.cl_guide) ConstraintLayout mClGuide;

    protected static GuideFragment newInstance(int layoutRes) {
        Bundle args = new Bundle();
        args.putInt(Constant.KEY_GUIDE_RES, layoutRes);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.TransparentDialog);
    }

    @Override
    protected int getFragmentLayout() {
        return getArguments().getInt(Constant.KEY_GUIDE_RES);
    }

    @Override
    protected void initViewComponent(@Nullable Bundle savedInstanceState) {
        super.initViewComponent(savedInstanceState);
        adapterView(mKey);
        if (getView() != null) {
            getView().setOnClickListener(this);
        }
    }

    /**
     * @param activity
     * @param layoutRes 布局资源文件
     * @param key 唯一标识，区分是否显示过，通过SharedPreferences进行保存
     */
    public static void show(AppCompatActivity activity, int layoutRes, String key) {
        mKey = key;
        if (!sHasShowSet.contains(key)) {
            if (getNeedShowGuide(activity,key)) {
                setNoNeedShowGuide(activity,key);
                newInstance(layoutRes)
                        .show(activity.getSupportFragmentManager(),activity.getClass().getSimpleName());
            }
            sHasShowSet.add(key);
        }
    }

    /**
     * 根据实际情况适配自己的布局
     * @param key 根据key区分哪个引导页进行不同的适配
     */
    private void adapterView(String key) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mClGuide.getLayoutParams();
        mClGuide.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    protected static boolean getNeedShowGuide(Context context,String key) {
        return SPUtils.getInstance(context, GUIDE_PROP).getBoolean(key, true);
    }


    protected static void setNoNeedShowGuide(Context context,String key) {
        SPUtils.getInstance(context, GUIDE_PROP).put(key, false);
    }
}
