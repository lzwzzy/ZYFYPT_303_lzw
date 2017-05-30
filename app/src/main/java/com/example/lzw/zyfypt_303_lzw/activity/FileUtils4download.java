package com.example.lzw.zyfypt_303_lzw.activity;

import android.content.Context;
import android.os.Environment;

import com.example.lzw.zyfypt_303_lzw.callback.HttpCallBack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/27 0027 15:56
 */
public class FileUtils4download {


    public static File createFile(Context context, String filename) {


        File file = null;
        String filefolder1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zyfypt-temp/";
        String filefolder2 = context.getCacheDir().getAbsolutePath() + "/zyfypt-temp/";

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            file = new File(filefolder1);

        } else {
            file = new File(filefolder2);
        }
        if (!file.exists()) file.mkdir();
        String filepath = "";
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            filepath = filefolder1 + filename + ".pdf";

        } else {
            filepath = filefolder2 + filename + ".pdf";
        }
        file = new File(filepath);
        // String filepath1=Environment.getExternalStorageDirectory().getAbsolutePath()+"/zyfypt-temp/"+filename+".pdf";
        // String filepath2=context.getCacheDir().getAbsolutePath()+"/zyfypt-temp/"+filename+".pdf";



     /*   if(state.equals(Environment.MEDIA_MOUNTED)){

            file = new File(filepath1);
        }else {
            file = new File(filepath2);
        }*/

        //L.d("vivi","file "+file.getAbsolutePath());
        System.out.println("----" + "file " + file.getAbsolutePath());

        return file;

    }


    public static void writeFile2Disk(Response<ResponseBody> response, File file, HttpCallBack httpCallBack) {


        long currentLength = 0;
        OutputStream os = null;

        InputStream is = response.body().byteStream();
        long totalLength = response.body().contentLength();

        try {

            os = new FileOutputStream(file);


            int len;

            byte[] buff = new byte[1024];

            while ((len = is.read(buff)) != -1) {

                os.write(buff, 0, len);
                currentLength += len;
                //L.d("vivi","当前进度:"+currentLength);
                System.out.println("----" + "当前进度:" + currentLength);
                httpCallBack.onLoading(currentLength, totalLength);
            }
            // httpCallBack.onLoading(currentLength,totalLength,true);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
