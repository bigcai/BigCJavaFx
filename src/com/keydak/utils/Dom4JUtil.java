package com.keydak.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * User: caisz
 * Date: 2017/5/18
 * Time: 11:51
 * Description:
 */
public class Dom4JUtil {

    public static Document readDocumentFromFile( File file ){
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        Document document = null;
        // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
        try {
            document = reader.read( file );
        } catch (DocumentException e) {
        }
        return document;
    }

    public static boolean saveDocumentToFile( Document document, File file ){
        String BACK_SUFFIX = ".back";

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");// 设置XML文件的编码格式
        XMLWriter writer = null;
        File backFile = null;
        try {
            // 保存前备份文件
            String backPath = file.getAbsolutePath() + BACK_SUFFIX;
            backFile = new File(backPath);
            if( backFile.exists() ) {
                backFile.delete();
            }
            copyFile( file.getAbsolutePath(), backPath );

            writer = new XMLWriter(new FileOutputStream(file), format); // 该语句会清空磁盘文件
            writer.write(document);
            writer.close();

        } catch (IOException e) {
            // 保存失败后从备份文件中恢复
            if( file.exists() ) {
                file.delete();
            }
            copyFile( file.getAbsolutePath() + BACK_SUFFIX, file.getAbsolutePath()  );

            return false;
        } finally {
            // 保存后删除备份
            if( backFile.exists() ) {
                backFile.delete();
            }
        }
        return true;
    }

    public static int copyFile(String src, String dst) {
        try {
            Files.copy(new File(src).toPath(), new File(dst).toPath());
            return 0;
        } catch (IOException e) {
            return -1;
        }
    }


    // 示例
    public static void main(String[] args) throws IOException {
        Document document = null;
        document = readDocumentFromFile(new File( "D:/test.xml" ));
        if(document != null) {
            Element commandStore = document.getRootElement();
            System.out.println( commandStore.getText() );
            commandStore.setText("你好!");
            System.out.println( commandStore.getText() );
        }
        saveDocumentToFile(document, new File( "D:/test.xml" ));

    }
}
