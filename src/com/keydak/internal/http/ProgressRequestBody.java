package com.keydak.internal.http;

import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;

/**
 * User: caisz
 * Date: 2017/6/30
 * Time: 16:26
 * Description:
 */
@Data
@NoArgsConstructor
public class ProgressRequestBody extends RequestBody {

    private MediaType mediaType;
    private File file;
    private ProgressListener listener;

    public static ProgressRequestBody create(MediaType mediaType, File file, ProgressListener listener) {
        ProgressRequestBody requestBody = new ProgressRequestBody();
        requestBody.setMediaType(mediaType);
        requestBody.setFile(file);
        requestBody.setListener(listener);
        return requestBody;
    }

    @Override public MediaType contentType() {
        return this.mediaType;
    }

    @Override public long contentLength() {
        return this.file.length();
    }

    @Override public void writeTo(BufferedSink sink) throws IOException {
        Source source;
        try {
            source = Okio.source(file);
            //sink.writeAll(source);
            Buffer buf = new Buffer();
            Long remaining = contentLength();
            for (long readCount; (readCount = source.read(buf, 2048)) != -1; ) {
                // 上传2048Byte流数据
                sink.write(buf, readCount);
                // 更新进度条
                this.listener.onProgress(contentLength(), remaining -= readCount, remaining == 0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface ProgressListener {
        void onProgress(long totalBytes, long remainingBytes, boolean done);
    }
}
