package com.example.lzw.zyfypt_303_lzw.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.callback.HttpCallBack;
import com.example.lzw.zyfypt_303_lzw.service.DownloadService;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewTwareActivity extends AppCompatActivity implements OnPageChangeListener {


    @BindView(R.id.pdfview)
    PDFView pdfView;
    @BindView(R.id.textView10)
    TextView tvinfo;
    @BindView(R.id.textView11)
    TextView tvpage;

    private String name="";
    private String attach="";

    private String BASEURL ="http://amicool.neusoft.edu.cn/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tware);
        ButterKnife.bind(this);
        attach=getIntent().getStringExtra("pdfattach");
        name=getIntent().getStringExtra("name");
        downloadfile();//下载文件
    }

    private void downloadfile() {
        String downloadUrl = "/Uploads/"+attach;    //补全pdf文件相对地址
        //定义Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //定义service
        DownloadService downloadService = retrofit.create(DownloadService.class);
        //定义call
        Call<ResponseBody> responseBodyCall = downloadService.downloadFile(downloadUrl);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                System.out.println("----"+response.message()+" length "+response.body().contentLength()+" type "+response.body().contentType());
                //建立一个文件
                final File file = FileUtils4download.createFile(ViewTwareActivity.this,name);
                //下载文件放在子线程
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        //保存到本地
                        FileUtils4download.writeFile2Disk(response, file, new HttpCallBack() {
                            @Override
                            public void onLoading(final long current, final long total) {
                                /**更新进度条*/
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        tvinfo.setText(current+"");//当前进度
                                        System.out.println("----"+current+"--totale:"+total);

                                        if(current==total)  //如果达到最大值
                                        {
                                            tvinfo.setText("下载完成");
                                            tvinfo.setVisibility(View.GONE);//不可见
                                            String state = Environment.getExternalStorageState();
                                            String pdfName="";
                                            if(state.equals(Environment.MEDIA_MOUNTED)){
                                                pdfName=Environment.getExternalStorageDirectory().getAbsolutePath()+"/zyfypt-temp/"+name+".pdf";
                                            }
                                            else pdfName=getCacheDir().getAbsolutePath()+"/zyfypt-temp/"+name+".pdf";

                                            display(pdfName, false);
                                        }
                                    }
                                });
                            }
                        });
                    }
                }.start();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void display(String assetFileName, boolean jumpToFirstPage) {
        if (jumpToFirstPage)
            setTitle(assetFileName);
        File file = new File(assetFileName);
        pdfView.fromFile(file)
                //.pages(0, 0, 0, 0, 0, 0) // 默认全部显示，pages属性可以过滤性显示
                .defaultPage(1)//默认展示第一页
                .onPageChange(this)//监听页面切换
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        tvpage.setText(page + "/" + pageCount);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


