package com.keydak.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * User: caisz
 * Date: 2017/6/12
 * Time: 16:08
 * Description:
 */
public class FileUtil {
    /**
     * 拷贝文件
     * @param src
     * @param dst
     * @return
     */
    public static int copyFile(String src, String dst) {
        try {
            Files.copy(new File(src).toPath(), new File(dst).toPath());
            return 0;
        } catch (IOException e) {
            return -1;
        }
    }


    public static void copyDirectoryAndFile(String src, String des) {
        File srcFile=new File(src);
        File[] fs=srcFile.listFiles();
        File desFile=new File(des);

        if(!desFile.exists()){
            desFile.mkdirs();
        }
        for (File f : fs) {
            if(f.isFile()){
                copyFile(f.getPath(), des + File.separator + f.getName()); //调用文件拷贝的方法
            }else if(f.isDirectory()){
                copyDirectoryAndFile(f.getPath(), des + File.separator + f.getName());
            }
        }

    }


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir + File.separator +children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /** ========================= 流式文件拷贝 ================================ */

    /**
     * 从JAR中复制文件到磁盘
     * @param srcFilePath：源路径，既JAR包中的资源文件，路径相对于CLASSPATH
     * @param destFilePath：目标路径，磁盘上的任意路径，绝对路径（一般为用户选择的文件夹路径）
     * @return int：返回执行后的状态；0：失败；1：成功；（可以扩充其它状态）
     */
    public static int copyFileInJAR(String srcFilePath, String destFilePath) throws IOException {

        int flag = 0;
        BufferedInputStream fis = null;
        FileOutputStream fos = null;
        File destFile = new File(destFilePath);
        if( destFile.exists() ) {
            destFile.delete();
        }
        new File(destFilePath.substring(0,destFilePath.lastIndexOf("/"))).mkdirs();
        destFile.createNewFile();
        try {
            fis = new BufferedInputStream(ClassLoader.getSystemResourceAsStream(srcFilePath));
            fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int c = 0;
            while ((c = fis.read(buf)) != -1) {
                fos.write(buf, 0, c);
            }
            flag = 1;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
        }
        return flag;
    }

    /**
     * copy from classPath to absolutePath
     * @param relativeClassPath 注意，必须要以/正斜杠为分隔符 eg: "resource/aaa(.*)"
     * @param absolutePath c:/
     */
    public static void copyDirInJAR( String relativeClassPath, String absolutePath ) {
        try {
            URL classResourceURL = Thread.currentThread().getContextClassLoader().getResource(relativeClassPath);
            if(classResourceURL.getProtocol().equals("file")) {
                copyDirectoryAndFile(Thread.currentThread().getContextClassLoader().getResource(relativeClassPath).getFile(), absolutePath);

            } else  if(classResourceURL.getProtocol().equals("jar")) {
                String classResourcePath = classResourceURL.getPath();
                // 开发环境里class和resource同位于target/classes目录下
                String classesDirPath = classResourcePath.substring(classResourcePath.indexOf("/") + 1, classResourcePath.indexOf(relativeClassPath)-2);
                JarFile jarFile = new JarFile(URLDecoder.decode(classesDirPath, "UTF-8"));
                Enumeration jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements()) {
                    JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
                    String resourceName = jarEntry.getName();
                    if ( resourceName.matches(relativeClassPath + "(.*)") && !jarEntry.isDirectory()) {
                        copyFileInJAR(resourceName, absolutePath + File.separator + resourceName.substring( resourceName.indexOf(relativeClassPath) + relativeClassPath.length() ));
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
