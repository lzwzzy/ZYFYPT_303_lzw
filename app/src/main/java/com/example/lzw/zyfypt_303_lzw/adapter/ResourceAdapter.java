package com.example.lzw.zyfypt_303_lzw.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.activity.DetailActivity;
import com.example.lzw.zyfypt_303_lzw.activity.ViewTwareActivity;
import com.example.lzw.zyfypt_303_lzw.activity.ViewVideoActivity;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;
import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by lzw on 2017/4/13.
 *
 */

public class ResourceAdapter extends RecyclerView.Adapter {
    private List<ResourceBean> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private MyApplication app;

    public ResourceAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    //自定义，设置数据
    public void setList(List<ResourceBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ResourceBean resourceBean = list.get(position);
        if (resourceBean == null) {
            return;
        }
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvtitle.setText(resourceBean.getName());
        viewHolder.tvdescript.setText(resourceBean.getDescription());
        viewHolder.tvtime.setText(resourceBean.getUpdate_time());

        Picasso.with(context)
                .load("http://amicool.neusoft.edu.cn/Uploads/" + resourceBean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
//                notifyDataSetChanged();//更新列表
                notifyItemRemoved(position);
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = list.get(position).getId();
                int userid = list.get(position).getUserid();
                String pdfattach = list.get(position).getPdfattach();
                String videopath = list.get(position).getVideopath();
                String name = list.get(position).getName();

                if (pdfattach != null){
                    Intent intent = new Intent(context, ViewTwareActivity.class);
                    intent.putExtra("pdfattach",pdfattach);
                    intent.putExtra("name",name);
                    intent.putExtra("resid",id);
                    intent.putExtra("userid",userid);
                    context.startActivity(intent);
                }else if (videopath != null){
                    Intent intent1 = new Intent(context, ViewVideoActivity.class);
                    intent1.putExtra("name",name);
                    intent1.putExtra("videopath",videopath);
                    intent1.putExtra("resid",id);
                    intent1.putExtra("userid",userid);
                    context.startActivity(intent1);
                }else {
                    //Toast.makeText(context,pdfattach+name,Toast.LENGTH_SHORT).show();
                    //跳转到详情页
                    Intent intent2 = new Intent(context, DetailActivity.class);
                    intent2.putExtra("resid",id);
                    intent2.putExtra("userid",userid);
                    intent2.putExtra("name",name);
                    context.startActivity(intent2);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvtitle, tvdescript, tvtime;
        private Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tvtitle = (TextView) itemView.findViewById(R.id.textView);
            tvdescript = (TextView) itemView.findViewById(R.id.textView2);
            tvtime = (TextView) itemView.findViewById(R.id.textView3);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }

}
