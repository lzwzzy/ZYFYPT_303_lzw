package com.example.lzw.zyfypt_303_lzw.fragment.focus;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.adapter.ResourceAdapter;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;
import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;
import com.example.lzw.zyfypt_303_lzw.listener.ResourceListener;
import com.example.lzw.zyfypt_303_lzw.model.FocusUserResListModel;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by lzw on 2017/6/1.
 */

public class FocusFragment extends Fragment {
    private int page = 1;
    private int lastVisibleItemPosition;//最后一条可见条目的位置
    private String mod, sessionid;
    private int userid;
    private MyApplication app;
    private View view = null;
    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;//显示效果
    private ResourceAdapter adapter;
    private List<ResourceBean> list = null;


    ResourceListener listener = new ResourceListener() {
        @Override
        public void onResponse(List<ResourceBean> beanlist) {
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
        public void onFile(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            mod = getArguments().getString("text");
            userid = getArguments().getInt("userid");
            Log.d(TAG, "onCreate: " + mod);//TODO
            app = (MyApplication) getActivity().getApplication();
            sessionid = app.getSessionid();
            Log.d(TAG, "onCreate: " + sessionid);//TODO
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        //如果未登录，显示->sessionid=null
        if (sessionid == null || sessionid.equals("")) {
            TextView textView = new TextView(getActivity());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(24);
            textView.setText("暂无数据，请先登录");
            return textView;
        }
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_focus_res_list, container, false);
            recyclerViewinit();
            FocusUserResListModel model = new FocusUserResListModel();
            model.getResultList(mod, page, sessionid, userid, listener);
        }
        return view;
    }

    private void recyclerViewinit() {
        adapter = new ResourceAdapter(context);
        //获取布局上的控件，注意通过view获取
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_focus);
        layoutManager = new LinearLayoutManager(context);
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
                    FocusUserResListModel model1 = new FocusUserResListModel();
                    model1.getResultList(mod, page, sessionid, userid, listener);
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


}
