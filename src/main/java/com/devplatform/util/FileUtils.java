package com.devplatform.util;

import java.io.*;
import java.nio.file.Files;

public class FileUtils {

    public static void printFile(File f, File t){
        if (f.isDirectory()){
            if (!t.exists()){
                t.mkdirs();
            }
        }else {
            copyFile(f,t);
        }
        if (f.isDirectory()){
            File[] files = f.listFiles();
            for (File temp : files){
                String n = t.getPath() + File.separator + temp.getName();
                File next = new File(n);
                printFile(temp,next) ;
            }
        }
    }


    public static void copyFile(File file1, File file2){
        OutputStream os = null;
        InputStream is = null;
        try {
            os = new FileOutputStream(file2);
            is = new FileInputStream(file1);
            byte[] b = new byte[1024*4];
            int item = -1;
            while ((item = is.read(b))!=-1){
                os.write(b,0,item);
            }
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os!=null){
                    os.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
