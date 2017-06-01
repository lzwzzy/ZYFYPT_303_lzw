package com.example.lzw.zyfypt_303_lzw.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.activity.DetailActivity;
import com.example.lzw.zyfypt_303_lzw.activity.ViewTwareActivity;
import com.example.lzw.zyfypt_303_lzw.activity.ViewVideoActivity;
import com.example.lzw.zyfypt_303_lzw.bean.CollectBean;
import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lzw on 2017/6/1.
 */

public class CollectListAdapter extends RecyclerView.Adapter {

    private Context context;//上下文
    private LayoutInflater layoutInflater;//动态加载布局
    private List<CollectBean<ResourceBean>> list;//保存要显示的数据

    public CollectListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.imageView)
        private ImageView imageView;
        //@BindView(R.id.textView)
        private TextView tvtitle;
        //@BindView(R.id.textView2)
        private TextView tvdescrrpt;
        //@BindView(R.id.textView3)
        private TextView tvtime;
        //@BindView(R.id.button)
        private Button button;

        ViewHolder(View itemView) {
            super(itemView);
            //ButterKnife.bind(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tvtitle = (TextView) itemView.findViewById(R.id.textView);
            tvdescrrpt = (TextView) itemView.findViewById(R.id.textView2);
            tvtime = (TextView) itemView.findViewById(R.id.textView3);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_fragment_collect, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ResourceBean resourceBean = list.get(position).getBean();
        if (resourceBean == null || list.size() == 0)
            return;
        final ViewHolder viewHolder = (ViewHolder) holder;
        //viewHolder.imageView.setImageResource(articleBean.getImgid());
        viewHolder.tvtitle.setText(resourceBean.getName());
        viewHolder.tvdescrrpt.setText("描述：" + resourceBean.getDescription());
        viewHolder.tvtime.setText(resourceBean.getUpdate_time());
        //异步加载图片
        Picasso.with(context)
                .load("http://amicool.neusoft.edu.cn/Uploads/"
                        + resourceBean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = list.get(position).getResid();
                int userid = list.get(position).getUserid();
                String pdfattach = list.get(position).getBean().getPdfattach();
                String videopath = list.get(position).getBean().getVideopath();
                String name = list.get(position).getBean().getName();

                if (pdfattach != null) {
                    Intent intent = new Intent(context, ViewTwareActivity.class);
                    intent.putExtra("pdfattach", pdfattach);
                    intent.putExtra("name", name);
                    intent.putExtra("resid", id);
                    intent.putExtra("userid", userid);
                    context.startActivity(intent);
                } else if (videopath != null) {
                    Intent intent1 = new Intent(context, ViewVideoActivity.class);
                    intent1.putExtra("name", name);
                    intent1.putExtra("videopath", videopath);
                    intent1.putExtra("resid", id);
                    intent1.putExtra("userid", userid);
                    context.startActivity(intent1);
                } else {
                    //Toast.makeText(context,pdfattach+name,Toast.LENGTH_SHORT).show();
                    //跳转到详情页
                    Intent intent2 = new Intent(context, DetailActivity.class);
                    intent2.putExtra("resid", id);
                    intent2.putExtra("userid", userid);
                    intent2.putExtra("name", name);
                    context.startActivity(intent2);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else
            return list.size();

    }

    public void setList(List<CollectBean<ResourceBean>> list) {
        this.list = list;
    }

}
