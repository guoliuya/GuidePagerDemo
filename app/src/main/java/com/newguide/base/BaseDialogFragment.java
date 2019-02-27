package com.newguide.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author Andevel
 */

public abstract class BaseDialogFragment extends DialogFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return getFragmentLayout() != -1 ? inflater.inflate(getFragmentLayout(), container, false) : null;
    }

    protected int getFragmentLayout() {
        return -1;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        initViewComponent(savedInstanceState);
    }

    protected void initViewComponent(@Nullable Bundle savedInstanceState) {
        this.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    return BaseDialogFragment.this.onBackPressed();
                } else if (keyCode == KeyEvent.KEYCODE_MENU) {
                    return false;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    protected boolean onBackPressed() {
        return false;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
