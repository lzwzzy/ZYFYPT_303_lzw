package com.example.lzw.zyfypt_303_lzw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;
import com.example.lzw.zyfypt_303_lzw.fragment.article.ArticleFragment;
import com.example.lzw.zyfypt_303_lzw.fragment.project.ProjectFragment;
import com.example.lzw.zyfypt_303_lzw.fragment.tcase.TcaseFragment;
import com.example.lzw.zyfypt_303_lzw.fragment.twear.TwareFragment;
import com.example.lzw.zyfypt_303_lzw.fragment.video.VideoFragment;
import com.example.lzw.zyfypt_303_lzw.model.LogoutModel;
import com.startsmake.mainnavigatetabbar.widget.MainNavigateTabBar;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    private ImageView mImageView, mLogoutBtn;
    private MyApplication application;
    private String sessionid;

    private NavigationView navigationView;
    private MainNavigateTabBar navigateTabBar;

    private Toolbar toolbar;

    private TextView tvUserName;

    private TextView tvPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavigationView();
        init(savedInstanceState);


        tvUserName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvUserName);
        tvPhoneNumber = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvPhoneNumber);
        tvPhoneNumber.setVisibility(View.GONE);
        mLogoutBtn.setVisibility(View.GONE);
        application = (MyApplication) getApplication();
        sessionid = application.getSessionid();
        if (sessionid == null || sessionid.equals("")) {
            tvUserName.setText("点击头像登录");
            tvPhoneNumber.setVisibility(View.GONE);
        } else {
            tvPhoneNumber.setVisibility(View.VISIBLE);
            mLogoutBtn.setVisibility(View.VISIBLE);
            tvUserName.setText(application.getUsername());
            tvPhoneNumber.setText(application.getRolename());
        }


    }

    /**
     * 初始化侧边栏
     */
    private void initNavigationView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (sessionid == null || sessionid.equals("")) {
            toolbar.setTitle("资源复用平台lzw");
        }

        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mImageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ivAvatar);
        mLogoutBtn = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.logout_btn);
        mImageView.setOnClickListener(this);
        mLogoutBtn.setOnClickListener(this);

        ButterKnife.bind(this);
    }

    /**
     * 初始化TabBar
     *
     * @param savedInstanceState
     */
    private void init(Bundle savedInstanceState) {
        navigateTabBar = (MainNavigateTabBar) findViewById(R.id.navigateTabBar);
        //对应xml中的containerId
        navigateTabBar.setFrameLayoutId(R.id.main_container);
        //对应xml中的navigateTabTextColor
        navigateTabBar.setTabTextColor(getResources().getColor(R.color.tab_text_normal));
        //对应xml中的navigateTabSelectedTextColor
        navigateTabBar.setSelectedTabTextColor(getResources().getColor(R.color.colorPrimary));

        //恢复选项状态
        navigateTabBar.onRestoreInstanceState(savedInstanceState);

        navigateTabBar.addTab(ArticleFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.articlee, R.mipmap.article_1, R.string.tag_bar_text_article));

        navigateTabBar.addTab(TcaseFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.tcase, R.mipmap.tcase_1, R.string.tag_bar_text_tcase));

        navigateTabBar.addTab(TwareFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.twear, R.mipmap.twear_1, R.string.tag_bar_text_twear));

        navigateTabBar.addTab(ProjectFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.project, R.mipmap.project_1, R.string.tag_bar_text_project));

        navigateTabBar.addTab(VideoFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.video, R.mipmap.video_1, R.string.tag_bar_text_video));

        navigateTabBar.setTabSelectListener(new MainNavigateTabBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(MainNavigateTabBar.ViewHolder holder) {
                toolbar.setTitleMarginStart(340);
                switch (holder.tabIndex) {
                    case 0:
                        toolbar.setTitle(R.string.tag_bar_text_article);
                        application.setMod("article");
                        break;
                    case 1:
                        toolbar.setTitle(R.string.tag_bar_text_tcase);
                        application.setMod("tcase");
                        break;
                    case 2:
                        toolbar.setTitle(R.string.tag_bar_text_twear);
                        application.setMod("tware");
                        break;
                    case 3:
                        toolbar.setTitle(R.string.tag_bar_text_project);
                        application.setMod("project");
                        break;
                    case 4:
                        toolbar.setTitle(R.string.tag_bar_text_video);
                        application.setMod("video");
                        break;
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
        navigateTabBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        String string = null;
        switch (id) {
            case R.id.nav_me:

                if (sessionid == null) {
                    string = "请先登陆";
                } else {
                    Intent intent = new Intent(this, UserInfoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.nav_about:
                string = "关于";
                break;
            case R.id.nav_friend:
                string = "关注";
                break;
            case R.id.nav_message:
                string = "私信";
                break;
            case R.id.nav_night:
                string = "夜间模式";
                break;
            case R.id.nav_notification:
                Intent intent = new Intent(this,CollectActivity.class);
                startActivity(intent);
                string = "收藏";
                break;
            case R.id.nav_setting:
                string = "设置";
                break;
            case R.id.nav_suggestion:
                string = "意见反馈";
                break;
            case R.id.nav_theme:
                string = "主题风格";
                break;
        }
        if (!TextUtils.isEmpty(string))
            Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.ivAvatar:
                if (sessionid == null || sessionid.equals("")) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "已登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.logout_btn:
                LogoutModel logoutModel = new LogoutModel();
                //如果注销成功
                if (logoutModel.logout(sessionid)) {
                    sessionid = null;
                    application.setSessionid(null);
                    mLogoutBtn.setVisibility(View.GONE);
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;


        }

    }


}
