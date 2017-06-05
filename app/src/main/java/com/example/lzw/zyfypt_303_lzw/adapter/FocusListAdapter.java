package com.example.lzw.zyfypt_303_lzw.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.activity.FocusUserResListActivity;
import com.example.lzw.zyfypt_303_lzw.bean.FocusResult;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;
import com.example.lzw.zyfypt_303_lzw.bean.UserBean;
import com.example.lzw.zyfypt_303_lzw.listener.FocusListener;
import com.example.lzw.zyfypt_303_lzw.model.FocusModel;

import java.util.List;

/**
 * Created by lzw on 2017/6/1.
 */

public class FocusListAdapter extends RecyclerView.Adapter {

    private Context context;//上下文
    private LayoutInflater layoutInflater;//动态加载布局
    private List<FocusResult<UserBean>> list;//保存要显示的数据

    public FocusListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_fragment_userlist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final FocusListener listener = new FocusListener() {
            @Override
            public void onResponse(String msg) {
                if (msg.equals("5")) {
                    list.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, "取消关注成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "取消关注失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        };
        UserBean userBean = list.get(position).getBean();
        if (userBean == null || list.size() == 0)
            return;
        final ViewHolder viewHolder = (ViewHolder) holder;
        //viewHolder.imageView.setImageResource(articleBean.getImgid());
        viewHolder.tvtitle.setText(userBean.getRealname());
        viewHolder.tvdescrrpt.setText(userBean.getRolename());
        viewHolder.tvtime.setText(list.get(position).getFwtime());

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication app = (MyApplication) context.getApplicationContext();
                FocusModel model = new FocusModel();
                model.unfocus("userfocus", list.get(position).getIdolid(), app.getSessionid(), listener);
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int userid = list.get(position).getIdolid();
                String realname = list.get(position).getBean().getRealname();
                Intent intent = new Intent(context, FocusUserResListActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("realname", realname);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else
            return list.size();

    }

    public void setList(List<FocusResult<UserBean>> list) {
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.imageView)
        //private ImageView imageView;
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
            //imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tvtitle = (TextView) itemView.findViewById(R.id.textView);
            tvdescrrpt = (TextView) itemView.findViewById(R.id.textView2);
            tvtime = (TextView) itemView.findViewById(R.id.textView3);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }

}
