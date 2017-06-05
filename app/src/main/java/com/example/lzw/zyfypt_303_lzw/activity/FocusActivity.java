package com.example.lzw.zyfypt_303_lzw.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.adapter.FocusListAdapter;
import com.example.lzw.zyfypt_303_lzw.bean.FocusResult;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;
import com.example.lzw.zyfypt_303_lzw.bean.UserBean;
import com.example.lzw.zyfypt_303_lzw.listener.FocusListListener;
import com.example.lzw.zyfypt_303_lzw.model.FocusListModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FocusActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;//显示效果
    private FocusListAdapter adapter;
    private List<FocusResult<UserBean>> list = null;
    private MyApplication app;
    private int page = 1;
    FocusListListener<UserBean> listener = new FocusListListener<UserBean>() {
        @Override
        public void onResponse(List<FocusResult<UserBean>> beanlist) {
            if (page == 1) {
                list = beanlist;
            } else {
                list.removeAll(beanlist);
                list.addAll(beanlist);
            }
            if (beanlist.size() != 0) {
                adapter.setList(list);//传给adapter
                adapter.notifyDataSetChanged();//通知更新
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(FocusActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };
    private int lastVisibleItemPosition;//最后一条可见条目的位置
    private String sessionid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        ButterKnife.bind(this);
        toolBarInit();
        app = (MyApplication) getApplication();
        sessionid = app.getSessionid();
        recyclerViewinit();
        FocusListModel model = new FocusListModel();
        model.getResultList("user", page, sessionid, listener);
    }

    private void toolBarInit() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("我关注的");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void recyclerViewinit() {
        adapter = new FocusListAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        //上拉监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    FocusListModel model1 = new FocusListModel();
                    model1.getResultList("user", page, sessionid, listener);
                    System.out.println("----onScrollStateChanged  page=" + page);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
                //lastVisibleItemPosition = list.size() - 1;

            }
        });
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
