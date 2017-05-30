package com.example.lzw.zyfypt_303_lzw.fragment.tcase;


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
import com.example.lzw.zyfypt_303_lzw.adapter.EndLessOnScrollListener;
import com.example.lzw.zyfypt_303_lzw.adapter.ResourceAdapter;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;
import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;
import com.example.lzw.zyfypt_303_lzw.listener.ResourceListener;
import com.example.lzw.zyfypt_303_lzw.model.ResourceModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by lzw on 2017/5/3.
 */

public class Tcase2Fragment extends Fragment {
    private int page = 1;
    private int count = 0;
    private String mText, sessionid;
    private MyApplication app;
    private View view = null;
    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;//显示效果
    private ResourceAdapter adapter;
    private ResourceModel resourceModel = new ResourceModel();
    private List<ResourceBean> list = new ArrayList<ResourceBean>();
    //数据获取
    private ResourceListener listener = new ResourceListener() {
        @Override
        public void onResponse(List<ResourceBean> beanlist) {
            count++;
            for (int i = 0; i < 10; i++) {
                list.add(beanlist.get(i));
            }
            if (count == 10) {
                List<ResourceBean> list1 = new ArrayList<ResourceBean>();
                for (int i = 0; i < list.size(); i++) {
                    if (mText.equals(list.get(i).getTechnoname())) {
                        Log.d(TAG, "list: " + list.get(i).getTechnoname());//TODO
                        list1.add(list.get(i));
                    }
                }
                adapter.setList(list1);
                adapter.notifyDataSetChanged();
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
            mText = getArguments().getString("text");
            Log.d(TAG, "onCreate: " + mText);//TODO
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
        Log.d(TAG, "onCreateView: " + mText + "T");//TODO
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tcase_2, container, false);
            recyclerViewinit();

            for (int p = 1; p < 11; p++) {
                resourceModel.getResultList("tcase", p, sessionid, listener);
            }

        }
        return view;
    }

    private void recyclerViewinit() {
        adapter = new ResourceAdapter(context);
        //获取布局上的控件，注意通过view获取
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_tcase);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        //上拉监听
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });
    }

    /**
     * 上拉加载更多数据
     */
    private void loadMoreData() {
        page += 1;
        if (page < 10) {
            resourceModel.getResultList("tcase", page, sessionid, listener);
        }

    }
}
