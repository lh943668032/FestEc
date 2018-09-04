package com.lh.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lh.core.fragments.StandardFragment;

public class ExampleFragment extends StandardFragment {

    @Override
    public Object setLayout() {
        return R.layout.fragment_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
