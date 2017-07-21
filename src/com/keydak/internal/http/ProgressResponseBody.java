package com.keydak.internal.http;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.*;

import java.io.IOException;

/**
 * User: caisz
 * Date: 2017/6/30
 * Time: 11:48
 * Description:
 *  具有下载进度【读取】功能，在创建OkHttpClient时什么拦截器，通过拦截器在OkHttpClient接受响应时将OKHttp原生的ResponseBody替换掉
 */
public class ProgressResponseBody extends ResponseBody {

    //回调接口
    public interface ProgressListener{
        /**
         * @param bytesRead 已经读取的字节数
         * @param contentLength 响应总长度
         * @param done 是否读取完毕
         */
        void update(long bytesRead,long contentLength,boolean done);
    }

    private final ResponseBody responseBody;
    private final ProgressListener progressListener;
    private BufferedSource bufferedSource;
    public long contentLength = -1;

    public ProgressResponseBody(ResponseBody responseBody,ProgressListener progressListener){
        this.responseBody = responseBody;
        this.contentLength = responseBody.contentLength();
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null){
            bufferedSource = Okio.buffer(this.source(responseBody.source(),this.contentLength));
        }
        return bufferedSource;
    }

    // 包装器
    private Source source(Source source, Long contentLength){
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink,byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;   //不断统计当前下载好的数据
                //接口回调
                progressListener.update(totalBytesRead, contentLength, bytesRead == -1);
                return bytesRead;
            }
        };
    }
}
