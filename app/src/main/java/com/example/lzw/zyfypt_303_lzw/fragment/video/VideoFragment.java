package com.example.lzw.zyfypt_303_lzw.fragment.video;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.adapter.TabFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzw on 2017/5/2.
 */

public class VideoFragment extends Fragment {

    private View view;
    private ViewPager viewPager;
    private Context context;
    private String[] titles = new String[]{"Android", "基础知识", "数据库", "PHP", "编程语言", "iOS"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_video, container, false);
            viewPager = (ViewPager) view.findViewById(R.id.viewPager_video);
            List<Fragment> fragments = new ArrayList<Fragment>();
            for (int i = 0; i < titles.length; i++) {
                Fragment fragment = new Video2Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("text", titles[i]);
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }
            viewPager.setAdapter(new TabFragmentAdapter(fragments, titles, getFragmentManager(), context));
            TabLayout tablayout = (TabLayout) view.findViewById(R.id.tablayout);
            tablayout.setupWithViewPager(viewPager);
            tablayout.setTabTextColors(getResources().getColor(R.color.colorGray), Color.WHITE);
        }
        return view;
    }
}
