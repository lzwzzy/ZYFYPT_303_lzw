package com.example.lzw.zyfypt_303_lzw.activity;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.adapter.TabFragmentAdapter;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;
import com.example.lzw.zyfypt_303_lzw.fragment.focus.FocusFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FocusUserResListActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private MyApplication application;
    private String[] titles = new String[]{"文章", "案例", "课件", "项目", "视频"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_user_res_list);
        ButterKnife.bind(this);
        toolBarInit();
        application = (MyApplication) getApplication();
        List<Fragment> fragments = new ArrayList<Fragment>();
        application.setMod("article");
        for (String title : titles) {
            Fragment fragment = new FocusFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("userid", getIntent().getIntExtra("userid", 1));
            switch (title) {
                case "文章":
                    bundle.putString("text", "article");
                    break;
                case "案例":
                    bundle.putString("text", "tcase");
                    break;
                case "课件":
                    bundle.putString("text", "tware");
                    break;
                case "项目":
                    bundle.putString("text", "project");
                    break;
                case "视频":
                    bundle.putString("text", "video");
                    break;
            }
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        viewPager.setAdapter(new TabFragmentAdapter(fragments, titles, getFragmentManager(), this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        application.setMod("article");
                        break;
                    case 1:
                        application.setMod("tcase");
                        break;
                    case 2:
                        application.setMod("tware");
                        break;
                    case 3:
                        application.setMod("project");
                        break;
                    case 4:
                        application.setMod("video");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(R.color.blank_color, Color.WHITE);
    }

    private void toolBarInit() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getIntent().getStringExtra("realname") + "的作品");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);//加载菜单布局
        return true;
    }
}
