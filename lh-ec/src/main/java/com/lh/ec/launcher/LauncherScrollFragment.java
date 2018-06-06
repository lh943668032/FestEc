package com.lh.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.lh.core.fragments.StandardFragment;
import com.lh.core.ui.launcher.LauncherHolderCreator;
import com.lh.core.ui.launcher.ScrollLauncherTag;
import com.lh.core.util.storage.LhPreference;
import com.lh.ec.R;

import java.util.ArrayList;

/**
 * @author lh
 * @datetime 2018/6/6 23:22
 */

public class LauncherScrollFragment extends StandardFragment implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static ArrayList<Integer> INTEGERS = null;

    private void initBanners(){
        INTEGERS = new ArrayList<>();
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner.setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanners();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if(position == INTEGERS.size() - 1){
            LhPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否已经登录
        }
    }
}
