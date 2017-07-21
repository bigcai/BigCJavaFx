package com.keydak.internal.http;

import okhttp3.*;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * User: vk
 * Date: 2017/4/28
 * Time: 16:24
 * Description:
 */
public class HttpUtils {

    //private static String host = "http://localhost:8080/rest";
    private static String host = "http://cloud.keydak.com/confighub/rest";

    /**
     * 同步的Get请求
     *
     * @param url
     * @return 字符串
     */
    public static String  getBodyAsString(String url, Map<String,Object> paramsMap) throws HttpRequestException {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String requestUrl = String.format("%s%s?%s", host, url, HttpUtils.toParamString(paramsMap) );
        final Request request = new Request.Builder()
            .url( requestUrl )
            .build();
        Call call = mOkHttpClient.newCall(request);
        Response execute = null;
        String json = "";
        try {
            execute = call.execute();
            if( execute.code() != 200 ) {
                throw new HttpRequestException();
            }
            json =  execute.body().string();

        } catch (Exception e) {
            throw new HttpRequestException();
        }
        return json;
    }


    /**
     * 同步的Post请求
     *
     * @param url
     * @return 字符串
     */
    public static String  postBodyAsString(String url, Map<String,Object> paramsMap) throws HttpRequestException {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String requestUrl = String.format("%s%s", host, url );
        String params =HttpUtils.toParamString(paramsMap);
        RequestBody requestBody = RequestBody.create( MediaType.parse("application/x-www-form-urlencoded"), params);
        final Request request = new Request.Builder()
                .url( requestUrl ).post(requestBody).build();
        Call call = mOkHttpClient.newCall(request);
        Response execute = null;
        String json = "";
        try {
            execute = call.execute();
            if( execute.code() != 200 ) {
                throw new HttpRequestException();
            }
            json =  execute.body().string();

        } catch (Exception e) {
            throw new HttpRequestException();
        }
        return json;
    }


    public static String toParamString( Map<String,Object> paramap) {

        if( paramap == null ) return "";

        StringBuffer tempParams = new StringBuffer();
        int pos = 0;
        for (String key : paramap.keySet()) {
            if (pos > 0) {
                tempParams.append("&");
            }
            try {
                Object value = paramap.get(key);
                String paramString =  value instanceof String?  (String) value : value.toString() ;
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode( paramString, "utf-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            pos++;
        }
        return tempParams.toString();
    }

    /**
     * 文件上传
     *
     * @param url
     * @param map
     * @param file
     */
    public static String postFile(final String url, final Map<String, Object> map, File file, ProgressRequestBody.ProgressListener progressListener) {
        OkHttpClient client = new OkHttpClient();
        String requestUrl = String.format("%s%s", host, url );
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(file != null){
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = ProgressRequestBody.create(MediaType.parse("application/octet-stream"), file, progressListener);
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", file.getName(), body);
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                if( entry.getKey() != null && entry.getValue() != null ) {
                    requestBody.addFormDataPart(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
                }
            }
        }
        Request request = new Request.Builder().url(requestUrl).post(requestBody.build()).build();
        // readTimeout("请求超时时间" , 时间单位);
        Response execute = null;
        try {
            execute = client.newBuilder().readTimeout(20*60*1000, TimeUnit.MILLISECONDS).build().newCall(request).execute();
            if( execute.code() != 200 ) {
                return "0";
            } else {
                return "1";
            }
        } catch (IOException e) {
            return "0";
        }
    }



    /**
     * 下载文件
     * @param fileUrl 文件url
     * @param destFileDir 存储目标目录
     */
    public static String downLoadFile(String fileUrl, Map<String,Object> paramsMap, final String destFileDir, ProgressResponseBody.ProgressListener listener) {
        // 插入下载进度监听器
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                //这里将ResponseBody包装成我们的ProgressResponseBody
                return response.newBuilder()
                        .body(new ProgressResponseBody(response.body(),listener))
                        .build();
            }
        }).build();
        String requestUrl = String.format("%s%s", host, fileUrl );
        String params =HttpUtils.toParamString(paramsMap);
        RequestBody requestBody = RequestBody.create( MediaType.parse("application/x-www-form-urlencoded"), params);

        final File file = new File(destFileDir);
        if (file.exists()) {
            file.delete();
        }
        final Request request = new Request.Builder().url(requestUrl).post(requestBody).build();
        final Call call = client.newCall(request);
        Response execute = null;
        try {
            execute = call.execute();
            if( execute.code() != 200 ) {
                return "0";
            } else {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                long total = execute.body().contentLength();
                long current = 0;
                is = execute.body().byteStream();
                new File(file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator))).mkdirs();
                file.createNewFile();
                fos = new FileOutputStream(file);
                while ((len = is.read(buf)) != -1) {
                    current += len;
                    fos.write(buf, 0, len);
                }
                fos.flush();
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
                return "1";
            }
        } catch (Exception e) {
            return "0";
        }
    }

}
